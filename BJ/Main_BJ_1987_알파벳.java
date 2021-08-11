import java.io.*;
import java.util.*;
// 210810

public class Main_BJ_1987_알파벳 {
    static int R, C, max;
    static char[][] map;
    static boolean[] v;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        map = new char[R][C];
        v = new boolean[26];

        for(int i=0; i<R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int idx = (int) (map[0][0] - 'A'); // 시작점
        v[idx] = true;

        dfs(0, 0, 1);

        System.out.println(max);
        br.close();
    }

    static void dfs(int r, int c, int cnt) {
        max = Math.max(max, cnt);

        // 상하좌우
        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(valid(nr, nc)) {
                int idx = (int) (map[nr][nc] - 'A');
                if(!v[idx]) { // 방문x
                    v[idx] = true;
                    dfs(nr, nc, cnt+1);
                    v[idx] = false;
                }
            }
        }
    }

    static boolean valid(int nr, int nc) {
        if(nr<0 || nr>=R || nc<0 || nc>=C) return false;
        return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}