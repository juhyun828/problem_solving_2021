import java.io.*;
import java.util.*;
// 210625

public class Main_BJ_1238_파티 {
    static int N, M, X;
    static int[][] adjMatrix;
    static int[] distFrom, distTo;

    static class Node implements Comparable<Node> {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
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
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        X = stoi(st.nextToken());
        adjMatrix = new int[N+1][N+1];

        distFrom = new int[N+1]; // 각 지점 -> X 지점 최단거리
        distTo = new int[N+1];  // 각 지점 <- X 지점 최단거리
        Arrays.fill(distFrom, Integer.MAX_VALUE);
        Arrays.fill(distTo, Integer.MAX_VALUE);

        for(int i=0; i<M; i++) { // 간선정보
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int w = stoi(st.nextToken());
            adjMatrix[from][to] = w;
        }

        dijkstra();

        int max = 0;
        for(int i=1; i<=N; i++) {
            if(i==X) continue;
            if(distFrom[i]==Integer.MAX_VALUE || distTo[i]==Integer.MAX_VALUE) continue;
            max = Math.max(max, distFrom[i] + distTo[i]);
        }

        System.out.println(max);

        br.close();
    }

    static void dijkstra() {
        boolean[] v = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작점
        distFrom[X] = 0;
        pq.offer(new Node(X, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(v[cur.v]) continue;
            v[cur.v] = true;

            for(int i=1; i<=N; i++) {
                if(adjMatrix[i][cur.v]==0) continue;
                if(distFrom[i] > cur.w + adjMatrix[i][cur.v]) {
                    distFrom[i] = cur.w + adjMatrix[i][cur.v];
                    pq.offer(new Node(i, distFrom[i]));
                }
            }
        }

        distTo[X] = 0;
        v = new boolean[N+1];
        pq.offer(new Node(X, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(v[cur.v]) continue;
            v[cur.v] = true;

            for(int i=1; i<=N; i++) {
                if(adjMatrix[cur.v][i]==0) continue;
                if(distTo[i] > cur.w + adjMatrix[cur.v][i]) {
                    distTo[i] = cur.w + adjMatrix[cur.v][i];
                    pq.offer(new Node(i, distTo[i]));
                }
            }
        }

    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

