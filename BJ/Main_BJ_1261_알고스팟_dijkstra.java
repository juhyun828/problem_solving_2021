import java.io.*;
import java.util.*;
// 210702

public class Main_BJ_1261_알고스팟_dijkstra {
    static int M, N; // 가로, 세로
    // 0, 0 -> N-1, M-1 이동
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static class Pos implements Comparable<Pos>{
        int r, c, cnt;
        public Pos(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pos o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            char[] chArr = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                map[i][j] = chArr[j]-'0';
            }
        }

        int min = dikstra();
        System.out.println(min);

        br.close();
    }

    static int dikstra() {
        int[][] dist = new int[N][M];
        for(int i=0; i<N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Pos> pq = new PriorityQueue<Pos>();
        // 시작점
        dist[0][0] = 0;
        pq.offer(new Pos(0, 0, 0));

        while(!pq.isEmpty()){
            Pos cur = pq.poll();
            if(dist[cur.r][cur.c] < cur.cnt) continue;

            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
                if (map[nr][nc]==0) { // 빈 방
                    if(dist[nr][nc] > cur.cnt) {
                        dist[nr][nc] = cur.cnt;
                        pq.offer(new Pos(nr, nc, dist[nr][nc]));
                    }

                } else { // 벽 부수고 가야 함
                    if(dist[nr][nc] > cur.cnt+1) {
                        dist[nr][nc] = cur.cnt+1;
                        pq.offer(new Pos(nr, nc, dist[nr][nc]));
                    }
                }
            }
        }
        return dist[N-1][M-1];
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
