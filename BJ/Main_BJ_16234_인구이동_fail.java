import java.io.*;
import java.util.*;
// 210604

public class Main_BJ_16234_인구이동_fail {
    static int N, L, R, ans;
    static int[][] map;
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
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        L = stoi(st.nextToken());
        R = stoi(st.nextToken());
        map = new int[N][N];
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
                print();
                break;
            }
            else {
                print();
            }

        }

        System.out.println(ans);

        br.close();
    }

    static boolean solve() {
        v = new boolean[N][N];
        boolean flag = false;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int d=0; d<4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
                    if(v[nr][nc]) continue;
                    int diff = Math.abs(map[i][j] - map[nr][nc]);
                    if(L<=diff && diff<=R) {
                        flag = true;
                        cnt = 2;
                        total = (map[i][j] + map[nr][nc]);
                        v[i][j] = true;
                        v[nr][nc] = true;
                        q.offer(new Pos(i, j));
                        q.offer(new Pos(nr, nc));
                        ++ans;
                        curV = new boolean[N][N];
                        curV[i][j] = true;
                        curV[nr][nc] = true;
                        bfs();
                    }
                }
            }
        }

        return flag;
    }

    static void bfs() {
        while(!q.isEmpty()) {
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
                    map[i][j] = divided;
                }
            }
        }
    }

    static void print() {
        System.out.println("=========" + ans + "===============");
        for(int i=0; i<N; i++)
            System.out.println(Arrays.toString(map[i]));

    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
