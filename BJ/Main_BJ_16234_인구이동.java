import java.io.*;
import java.util.*;
// 210606

public class Main_BJ_16234_인구이동 {
    static int N, L, R, ans;
    static int[][] map, newMap;
    static boolean[][] v, curV;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int cnt, total;
    static Queue<Pos> q;

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
        newMap = new int[N][N];

        q = new ArrayDeque<>();
        ans = 0;
        cnt = 0;
        total = 0;

        // 지도 입력
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        while(true) {
            boolean res = solve();
            if(!res){
                // 인구 이동이 일어나지 않으면 멈춘다.
                break;
            }
            else {
                ++ans;
            }
        }
        System.out.println(ans);

        br.close();

    }

    static boolean solve() {
        boolean move = false; /// 인구 이동 유무
        v = new boolean[N][N]; // 이번 턴에서의 방문 여부

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int d=0; d<4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=N || v[nr][nc]) continue;
                    int diff = Math.abs(map[i][j] - map[nr][nc]);
                    if(L<=diff && diff<=R) {
                        move = true;
                        cnt = 2;
                        total = (map[i][j] + map[nr][nc]);
                        v[i][j] = true; // 이번 턴에서 방문
                        v[nr][nc] = true;
                        // 이번 턴에서 방문한 두 지점에서 연결된 다른 구역 찾기
                        q.offer(new Pos(i, j));
                        q.offer(new Pos(nr, nc));
                        curV = new boolean[N][N]; // 이번 차례에서 방문하는 지점 따로 표기
                        curV[i][j] = true;
                        curV[nr][nc] = true;
                        bfs();
                    }
                }
            }
        }

        // 바뀐 인구 수 갱신
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(v[i][j]) {
                    map[i][j] = newMap[i][j];
                }
            }
        }

        return move;
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr<0 || nr>=N || nc<0 || nc>=N || v[nr][nc]) continue;
                int diff = Math.abs(map[cur.r][cur.c] - map[nr][nc]);

                if(L<=diff && diff<=R) {
                    cnt += 1;
                    total += map[nr][nc];
                    v[nr][nc] = true;
                    curV[cur.r][cur.c] = true;
                    curV[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                }
            }
        }

        int divided = total / cnt;
        // 인구 이동
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(curV[i][j]) {
                    newMap[i][j] = divided;
                }
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
