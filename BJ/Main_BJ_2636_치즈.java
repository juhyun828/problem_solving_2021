
import java.io.*;
import java.util.*;
// 210922

public class Main_BJ_2636_치즈 {
    static int H, W, cheeseCnt; // 세로, 가로
    static int[][] map; // 0: 빈칸, 1: 치즈
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static class Pos{
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        H = stoi(st.nextToken());
        W = stoi(st.nextToken());
        map = new int[H][W];
        cheeseCnt = 0;

        for(int i=0; i<H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<W; j++) {
                map[i][j] = stoi(st.nextToken());
                if(map[i][j]==1) ++cheeseCnt;
            }
        }

        solve();
        br.close();
    }

    static void solve() {
        int lastCheeseCnt = 0;
        int time = 1;

        for(time=1; ; time++) {
            lastCheeseCnt = cheeseCnt;
            bfs();
            if (cheeseCnt==0) break;
        }

        System.out.println(time);
        System.out.println(lastCheeseCnt);
    }

    static void bfs(){
        // 공기 칸 (0, 0)에서 시작해서 만나는 빈칸은 구멍이 아니라 공기다.
        boolean[][] v = new boolean[H][W];
        ArrayDeque<Pos> q = new ArrayDeque<>();
        v[0][0] = true;
        q.offer(new Pos(0, 0));

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            for (int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(!valid(nr, nc) || v[nr][nc]) continue;
                if(map[nr][nc]==0) { // 만나게 되는 공기는 다음 탐색 지점
                    v[nr][nc] = true;
                    q.offer(new Pos(nr, nc));

                } else { // 치즈를 만나면 녹임
                    v[nr][nc] = true;
                    map[nr][nc] = 0;
                    --cheeseCnt;
                }
            }
        }
    }

    static boolean valid(int nr, int nc) {
        if(nr<0 || nr>=H || nc<0 || nc>=W) return false;
        else return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}