import java.io.*;
import java.util.*;
// 210825

public class Main_BJ_1012_유기농배추 {
    static int M, N, K; // 가로, 세로
    static int[][] map;
    static boolean[][] v;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    static class Pos {
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = stoi(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            M = stoi(st.nextToken()); // 가로
            N = stoi(st.nextToken()); // 세로
            K = stoi(st.nextToken());
            int min = 0;

            map = new int[N][M];
            v = new boolean[N][M];

            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int c = stoi(st.nextToken()); // x
                int r = stoi(st.nextToken()); // y
                map[r][c] = 1;
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(map[i][j]==0) {
                        v[i][j] = true;
                    } else if(map[i][j]==1 && !v[i][j]) {
                        ++min;
                        v[i][j] = true;
                        bfs(i, j);
                    }
                }
            }
            System.out.println(min);
        }
    }


    static void bfs(int r, int c) {
        Queue<Pos> q = new ArrayDeque<Pos>();
        // 시작점
        q.offer(new Pos(r, c));
        v[r][c] = true;

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(!valid(nr, nc) || v[nr][nc] || map[nr][nc]!=1) continue;
                // 방문
                v[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }
    }

    static boolean valid(int nr, int nc) {
        if(nr<0 || nr>=N || nc<0 || nc>=M) return false;
        else return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}