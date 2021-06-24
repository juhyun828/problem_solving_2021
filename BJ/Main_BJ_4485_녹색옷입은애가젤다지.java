import java.io.*;
import java.util.*;
// 210624

public class Main_BJ_4485_녹색옷입은애가젤다지 {
    static int N;
    static int[][] map, dist;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static class Node implements Comparable<Node> {
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
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int tc=1; ; tc++) {
            N = stoi(br.readLine());
            if(N==0) break;
            map = new int[N][N];
            dist = new int[N][N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = stoi(st.nextToken());
                }
            }
            dijkstra();

            sb.append("Problem " + tc + ":" + " " + dist[N-1][N-1] + "\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static void dijkstra() {
        // 거리값 모두 최대값으로 초기화
        for(int i=0; i<N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 시작값
        dist[0][0] = map[0][0];
        PriorityQueue<Node> pq = new PriorityQueue();
        pq.offer(new Node(0, 0, dist[0][0]));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr<0 || nr>=N || nc<0 || nc>=N)  continue;;
                if(dist[nr][nc] > dist[cur.r][cur.c] + map[nr][nc]) {
                    dist[nr][nc] = dist[cur.r][cur.c] + map[nr][nc];
                    pq.offer(new Node(nr, nc, dist[nr][nc]));
                }
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

