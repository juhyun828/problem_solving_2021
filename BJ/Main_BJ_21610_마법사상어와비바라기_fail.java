import java.io.*;
import java.util.*;
// 210924

public class Main_BJ_21610_마법사상어와비바라기 {
    static int N, M;
    static int[][] map;
    static boolean[][] cloud;
    static int[] dr = new int[] {0, -1,-1,-1, 0, 1, 1, 1};
    static int[] dc = new int[] {-1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] acrossDr = new int[] {-1, -1, 1, 1}; // 대각선 개수 셀 때
    static int[] acrossDc = new int[] {-1, -1, 1, 1};

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());

        map = new int[N+1][N+1];
        cloud = new boolean[N+1][N+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=1; j<=N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        // 초기 구름 위치
        cloud[N][1] = true;
        cloud[N][2] = true;
        cloud[N-1][1] = true;
        cloud[N-1][2] = true;

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int di = stoi(st.nextToken());
            int si = stoi(st.nextToken());

            // // 1+2, 구름 칸 이동 + 모든 구름 칸 물의 양 +1
            cloud = moveCloud(di, si);
            
            // 3. 구름이 모두 사라진다.
            
            // 4. 2에서 물이 증가한 칸에 물복사버그 마법 시전
            map = copyWaterMagic();

            // 5. 물 2 이상인 모든 칸에 구름이 생기고 물 양 2 줄어든다. 3에서 구름 사라진 칸x
            cloud = makeNewCloud();
        }

        int total = getTotal();
        System.out.println(total);

        br.close();
    }

    static int getTotal() {
        int total = 0;
        for(int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                total += map[i][j];
            }
        }
        return total;
    }

    static boolean[][] moveCloud(int di, int si) { // 1+2, 구름 칸 이동 + 모든 구름 칸 물의 양 +1
        boolean[][] newCloud = new boolean[N+1][N+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if (cloud[i][j]) {
                   int nr = i + dr[di-1] * si;
                   int nc = j + dc[di-1] * si;
                   nr = checkRance(nr);
                   nc = checkRance(nc);
                   newCloud[nr][nc] = true;
                   map[nr][nc] += 1;
                }
            }
        }

        return newCloud;
    }

    static int checkRance(int ni) {
        while (ni < 1) ni += N;
        while (ni > N) ni -= N;
        return ni;
    }

    static int[][] copyWaterMagic() { // 4, 대각선 방향 거리 1인 칸에 물 있는 바구니 수 만큼 (r, c)에 물 양 증가
        int[][] newMap = new int[N+1][N+1];
        // copy
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++)
                newMap[i][j] = map[i][j];
        }

        // 물이 증가한 칸
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(cloud[i][j]) {
                    int cnt = countAcrossWater(i, j);
                    newMap[i][j] += cnt;
                }
            }
        }
        return newMap;
    }

    static int countAcrossWater(int r, int c) {
        int cnt = 0;
        for(int d=0; d<4; d++) {
            int nr = r + acrossDr[d];
            int nc = c + acrossDc[d];
            if(valid(nr, nc) && map[nr][nc]>0) ++cnt;
        }
        return cnt;
    }

    static boolean valid(int nr, int nc) {
        if(nr<1 || nr>N || nc<1 || nc> N) return false;
        else return true;
    }

    static boolean[][] makeNewCloud() { // newCloud -> cloud
        boolean[][] newCloud = new boolean[N+1][N+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(!cloud[i][j] && map[i][j]>=2) {
                    newCloud[i][j] = true;
                }
            }
        }

        return newCloud;
    }


    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
