import java.io.*;
import java.util.*;
// 210917

public class Main_BJ_5547_일루미네이션 {
    static int W, H;
    static int[][] map; // 시작 1,1
    static boolean[][] outside, v;
    static int[] oddDr = new int[] {-1,-1, 0, 0, 1, 1};
    static int[] oddDc = new int[] {0, 1, -1, 1, 0, 1};
    static int[] evenDr = new int[] {-1, -1, 0, 0, 1, 1};
    static int[] evenDc = new int[] {-1, 0, -1, 1, -1, 0};

    static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = stoi(st.nextToken());
        H = stoi(st.nextToken());
        map = new int[H+1][W+1];
        outside = new boolean[H+1][W+1];
        v = new boolean[H+1][W+1];
        for(int i=1; i<=H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=W; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        
        // 벽의 안쪽인지 바깥쪽인지 결정 -> 경계칸의 빈칸에서 시작에서 닿을 수 있는 빈칸은 벽의 바깥쪽이다.
        // i==1, i==W, j==1, j==H만 검사
        for(int j=1; j<=W; j++) {
            if(map[1][j]==0 && !v[1][j]) bfs(new Pos(1, j));
            if(map[H][j]==0 && !v[H][j]) bfs(new Pos(H, j));
        }
        for(int i=1; i<=H; i++) {
            if (map[i][1]==0 && !v[i][1]) bfs(new Pos(i, 1));
            if (map[i][W]==0 && !v[i][W]) bfs(new Pos(i, W));
        }

        int cnt = 0;
        for(int i=1; i<=H; i++) {
            for(int j=1; j<=W; j++) {
                if(map[i][j]==1) {
                    if(i%2==0) { // y축이 짝수
                        int res = checkEven(i, j);
                        cnt += res;
                    } else { // y축이 홀수
                        int res = checkOdd(i, j);
                        cnt += res;
                    }
                }
            }
        }

        System.out.println(cnt);

        br.close();
    }

    static void bfs(Pos start) {
        Queue<Pos> q = new ArrayDeque<Pos>();
        // 시작 빈칸
        q.offer(start);
        outside[start.y][start.x] = true;
        v[start.y][start.x] = true;

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            if(cur.y%2==0) { // 현재 행이 짝수 
                for(int d=0; d<6; d++) {
                    int nr = cur.y + evenDr[d];
                    int nc = cur.x + evenDc[d];
                    if(valid(nr, nc) && !v[nr][nc] && map[nr][nc]==0) {
                        if(map[nr][nc]==0) {
                            // 아직 방문하지 않았는데 빈칸이면 바깥쪽 빈칸임
                            v[nr][nc] = true;
                            outside[nr][nc] = true;
                            q.offer(new Pos(nr, nc));
                        } else {
                            v[nr][nc] = true;
                        }
                    }
                }

            } else { // 현재 행이 홀수
                for(int d=0; d<6; d++) {
                    int nr = cur.y + oddDr[d];
                    int nc = cur.x + oddDc[d];
                    if(valid(nr, nc) && !v[nr][nc] && map[nr][nc]==0) {
                        if(map[nr][nc]==0) {
                            // 아직 방문하지 않았는데 빈칸이면 바깥쪽 빈칸임
                            v[nr][nc] = true;
                            outside[nr][nc] = true;
                            q.offer(new Pos(nr, nc));
                        } else {
                            v[nr][nc] = true;
                        }
                    }
                }
            }
        }
    }

    static int checkOdd(int i, int j) {
        // 주위 6방향으로 벽이 있는지 제크
        int cnt = 0;
        for(int d=0; d<6; d++) {
            int nr = i + oddDr[d];
            int nc = j + oddDc[d];
            // 빈칸인데 바깥쪽 빈칸이어야 함. 벽들에 둘러쌓인 빈칸이면 안됨
            if(!valid(nr, nc) || (map[nr][nc]==0 && outside[nr][nc])) ++cnt;
        }
        return cnt;
    }

    static int checkEven(int i, int j) {
        int cnt = 0;
        for(int d=0; d<6; d++) {
            int nr = i + evenDr[d];
            int nc = j + evenDc[d];
            if(!valid(nr, nc) || (map[nr][nc]==0 && outside[nr][nc])) ++cnt;
        }
        return cnt;
    }

    static boolean valid(int nr, int nc) {
        if(nr<1 || nr>H || nc<1 || nc>W) return false;
        else return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}