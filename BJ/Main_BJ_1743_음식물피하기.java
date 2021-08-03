import java.io.*;
import java.util.*;
// 210530

public class Main_BJ_1743_음식물피하기 {
    static int N, M, K, max;
    static int[][] map;
    static boolean[][] v;
    static Queue<Pos> q;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static class Pos {
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input_BJ_1743_음식물피하기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());

        map = new int[N+1][M+1];
        v = new boolean[N+1][M+1];
        q = new ArrayDeque<>();
        max = 0;

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = stoi(st.nextToken());
            int c = stoi(st.nextToken());
            map[r][c] = 1;
        }

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(map[i][j]==1 && !v[i][j]) {
                    v[i][j] = true;
                    q.offer(new Pos(i, j));
                    bfs();
                }
            }
        }

        System.out.println(max);
        br.close();
    }

    static void bfs() {
        int size = 1;

        while(!q.isEmpty()) {
            Pos cur = q.poll();
            for(int d=0; d<4; d++){
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr<1 || nr>N || nc<1 || nc>M || v[nr][nc] || map[nr][nc]!=1) continue;
                q.offer(new Pos(nr, nc));
                v[nr][nc] = true;
                ++size;
            }
        }
        max = Math.max(max, size);
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
