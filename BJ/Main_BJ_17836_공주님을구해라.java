import java.io.*;
import java.util.*;
// 210923

public class Main_BJ_17836_공주님을구해라 {
    static int N, M, T;
    static int[][] map;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    static class Pos {
        int r, c, gram, time;
        public Pos(int r, int c, int gram, int time) {
            this.r = r;
            this.c = c;
            this.gram = gram;
            this.time = time;
        }
    }
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        T = stoi(st.nextToken());

        map = new int[N+1][M+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        int time = bfs();
        if(time == Integer.MAX_VALUE) System.out.println("Fail");
        else System.out.println(time);

        br.close();
    }

    static int bfs() {
        boolean[][][] v = new boolean[N+1][M+1][2];
        Queue<Pos> q = new ArrayDeque<Pos>();
        // 시작점
        if(map[1][1]==0) {
            v[1][1][0] = true;
            q.offer(new Pos(1, 1, 0, 0));

        } else if(map[1][1]==2) {
            v[1][1][1] = true;
            q.offer(new Pos(1, 1, 1, 0));
        }

        int min = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            if(cur.time > T) {
                continue;

            } else if(cur.r == N && cur.c ==M) {
                min = Math.min(min, cur.time);
            }

            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(!valid(nr, nc) || v[nr][nc][cur.gram]) continue;

                if(map[nr][nc]==0) {
                    v[nr][nc][cur.gram] = true;
                    q.offer(new Pos(nr, nc, cur.gram, cur.time+1));

                } else if(map[nr][nc]==1 && cur.gram==1) {
                    v[nr][nc][cur.gram] = true;
                    q.offer(new Pos(nr, nc, cur.gram, cur.time+1));

                } else if(map[nr][nc]==2) { // 그람이 있는 칸
                    v[nr][nc][0] = true;
                    v[nr][nc][1] = true;
                    q.offer(new Pos(nr, nc, 1, cur.time+1));
                }
            }
        }
        return min;
    }

    static boolean valid(int nr, int nc) {
        if(nr<1||nr>N||nc<1||nc>M) return false;
        else return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}