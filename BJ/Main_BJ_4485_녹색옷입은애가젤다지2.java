import java.io.*;
import java.util.*;
// 210702

public class Main_BJ_4485_녹색옷입은애가젤다지2 {
    static int N;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static class Node implements Comparable<Node>{
        int r, c, w;

        public Node(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = 1;

        while(true) {
            N = stoi(br.readLine());
            if(N==0) break;
            map = new int[N][N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = stoi(st.nextToken());
                }
            }
            int[][] dist = dikstra();
            System.out.println("Problem " + tc++ + ": " + dist[N-1][N-1]);
        }
        br.close();
    }

    static int[][] dikstra() {
        int[][] dist = new int[N][N];
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        // 시작점
        dist[0][0] = map[0][0];
        pq.offer(new Node(0, 0, dist[0][0]));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.r == N-1 && cur.c == N-1) return dist;

            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
                if(dist[nr][nc]==0) {
                    dist[nr][nc] = cur.w + map[nr][nc];
                    pq.offer(new Node(nr, nc, dist[nr][nc]));
                } else {
                    if(dist[nr][nc] > cur.w + map[nr][nc]) {
                        dist[nr][nc] = cur.w + map[nr][nc];
                        pq.offer(new Node(nr, nc, dist[nr][nc]));
                    }
                }
            }
        }

        return dist;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
