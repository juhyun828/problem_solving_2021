import java.io.*;
import java.util.*;
// 210910

public class Main_BJ_16234_인구이동_2 {
    static int N, L, R;
    static int[][] map, newMap;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int cnt, total;
    static boolean[][] v;

    static class Pos {
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
        N = stoi(st.nextToken());
        L = stoi(st.nextToken());
        R = stoi(st.nextToken());
        map = new int[N][N];

        // 지도 입력
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        int ans = 0;
        while(true) {
            boolean move = solve();
            if(!move) break;
            else ++ans;
        }

        System.out.println(ans);

        br.close();

    }
    
    static boolean solve() {
        boolean move = false; // 인구 이동 유무
        v = new boolean[N][N]; //이번 턴에서의 방문 여부
        cnt = 0; // 국경이 열린 나라 칸 수
        total = 0; // 국경이 열린 나라들의 인구 합
        newMap = new int[N][N]; // 이번 턴에서 이동된 인구 상태
        // bfs를 돌면서 map에 직접 접근해서 인구를 이동하면, 다음 bfs를 돌 때 이동된 인구 수를 기준으로 이동 여부를 판단하게 됨

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int d=0; d<4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if(!valid(nr, nc) || v[nr][nc]) continue;
                    int diff = Math.abs(map[i][j]-map[nr][nc]);
                    if(L<=diff && diff<=R) {
                        move = true; // 인구 이동 발생
                        cnt = 2;
                        total = map[i][j] + map[nr][nc];
                        // 이번 턴에서 방문
                        v[i][j] = true; v[nr][nc] = true;
                        bfs(new Pos(i, j), new Pos(nr, nc));
                    }
                }
            }
        }

        // 이번 턴에서 바뀐 인구 수 갱신
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(v[i][j]) {
                    map[i][j] = newMap[i][j];
                }
            }
        }

        return move;
    }

    static void bfs(Pos pos1, Pos pos2) {
        Queue<Pos> q = new ArrayDeque<Pos>();
        // 이번 bfs에서 방문하는 지점들 따로 표기 -> 이동한 인구들을 옮겨야 함
        boolean[][] curV = new boolean[N][N];
        q.offer(pos1); q.offer(pos2);
        curV[pos1.r][pos1.c] = true; curV[pos2.r][pos2.c] = true;

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(!valid(nr, nc) || v[nr][nc]) continue; // 이번 턴(solve)에서 방문x
                int diff = Math.abs(map[cur.r][cur.c]-map[nr][nc]);
                if(L<=diff && diff<=R) {
                    cnt += 1; // 합쳐지는 국격 수 추가
                    total += map[nr][nc];
                    v[nr][nc] = true;
                    curV[cur.r][cur.c] = true; curV[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                }
            }
        }

        // 하나의 열린 국경들 덩어리 사이에서 인구 이동이 일어난다.
        int divided = total / cnt;
        move(curV, divided);
    }

    static void move(boolean[][] curV, int divided) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if (curV[i][j]) {
                    newMap[i][j] = divided;
                }
            }
        }
    }

    static boolean valid(int nr, int nc) {
        if(nr<0 || nr>=N || nc<0 || nc>=N) return false;
        else return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
