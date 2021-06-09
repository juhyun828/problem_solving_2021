import java.io.*;
import java.util.*;
// 210609

public class Solution_연습_4317_칩생산_fail {
    static int max;
    static int H, W;
    static int[][] map; // 0빈칸, 1생산 불가, 2생산
    static boolean[][] v;
    static int[] dr = {0, 0, 1, 1};
    static int[] dc = {0, 1, 0, 1};

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = stoi(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            max = 0;
            st = new StringTokenizer(br.readLine(), " ");
            H = stoi(st.nextToken());
            W = stoi(st.nextToken());
            map = new int[H][W];
            v = new boolean[H][W];
            
            for(int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0; j<W; j++) {
                    map[i][j] = stoi(st.nextToken());
                }
            }

            dfs(0, 0, 0);
            sb.append("#" + tc +" " + max + "\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static void dfs(int r, int c, int chipCnt) {
        v[r][c] = true;
        if(possible(r, c)) {
            process(r, c);
            printMap();
            max = Math.max(max, chipCnt+1);
            for(int d=1; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(v[nr][nc]) continue;
                dfs(nr, nc, chipCnt+1);
            }

            // 생산 취소
            unprocess(r, c);
            for(int d=1; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(v[nr][nc]) continue;
                dfs(nr, nc, chipCnt);
            }
        } else {
            for(int d=1; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr<0 || nr>=H || nc<0 || nc>=W || v[nr][nc]) continue;
                dfs(nr, nc, chipCnt);
            }
        }
        v[r][c] = false;
    }

    static void process(int r, int c) {
        // 칩 생산
        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            map[nr][nc] = 2;
        }
    }

    static void unprocess(int r, int c) {
        // 생산했던 칩 취소
        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            map[nr][nc] = 0;
        }
    }

    static void printMap() {
        for(int i=0; i<H;i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println("==============");
    }

    static boolean possible(int r, int c) { // 생산 가능
        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr<0 || nr>=H || nc<0 || nc>=W) return false;
            if(map[nr][nc]!=0) return false;
        }
        return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
