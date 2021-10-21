import java.io.*;
import java.util.*;
// 211022

public class Main_BJ_14620_꽃길 {
    static int N, min;
    static int[][] map;
    static int[] res;
    static boolean[][] v;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0,0,-1, 1};
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
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        res = new int[3];
        min = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        combination(0, 0);
        System.out.println(min);

        br.close();
    }

    static void combination(int L, int cnt) {
        if(cnt>=3) {
            if(validPos(res)) {
                min = Math.min(min, calCost());
            }
            return;
        }

        if(L>=N*N) return;

        res[cnt] = L;
        combination(L+1, cnt+1);
        combination(L+1, cnt);
    }

    static int calCost() {
        int total = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(v[i][j]) {
                    total += map[i][j];
                }
            }
        }

        return total;
    }

    static boolean validPos(int[] res) {
        v = new boolean[N][N];

        for(int num: res) {
            Pos pos = calPos(num);
            v[pos.r][pos.c] = true;
            for(int d=0; d<4; d++) {
                int nr = pos.r + dr[d];
                int nc = pos.c + dc[d];
                if(!valid(nr, nc) || v[nr][nc]) return false;
                v[nr][nc] = true;
            }
        }

        return true;
    }
    static Pos calPos(int num) {
        return new Pos(num/N, num%N);
    }

    static boolean valid(int nr, int nc) {
        if(nr<0||nr>=N||nc<0||nc>=N) return false;
        else return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}