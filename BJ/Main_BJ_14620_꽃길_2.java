import java.io.*;
import java.util.*;
// 211022


public class Main_BJ_14620_꽃길_2 {
    static int N, min;
    static int[][] map;
    static boolean[][] v;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0,0,-1, 1};

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        v = new boolean[N][N];
        min = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(min);

        br.close();
    }

    static void dfs(int L) {
        if(L>=3) {
            // 값 계산해서 갱신
            min = Math.min(min, calCost());
            return;
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(validPos(i, j)) {
                    setVisited(i, j, true); // 방문 설정
                    dfs(L+1);
                    setVisited(i, j, false);// 방문 해제
                }
            }
        }
    }

    static int calCost() {
        int total = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(v[i][j]) total += map[i][j];
            }
        }

        return total;
    }

    static void setVisited(int r, int c, boolean flag) {
        v[r][c] = flag;

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            v[nr][nc] = flag;
        }
    }

    static boolean validPos(int r, int c) {
        if(v[r][c]) return false;

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(!valid(nr, nc) || v[nr][nc]) return false;
        }

        return true;
    }

    static boolean valid(int nr, int nc) {
        if(nr<0||nr>=N||nc<0||nc>=N) return false;
        else return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}