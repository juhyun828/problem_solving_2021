import java.io.*;
import java.util.*;
// 210617

public class Main_BJ_1753_최단경로_pq {
    static int V, E, start; // 정점, 간선 수, 시작 정점
    static int[][] adjMatrix;

    static class Node implements Comparable<Node> {
        int v, totalDistance;

        public Node(int v, int totalDistance) {
            this.v = v;
            this.totalDistance = totalDistance;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.totalDistance, o.totalDistance);
        }
    }


    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        start = stoi(br.readLine());
        adjMatrix = new int[V+1][V+1];

        for(int i=0; i<E; i++) { // 간선 정보 입력
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            adjMatrix[from][to] = weight;
        }

        dijkstra();
        
        br.close();
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] v = new boolean[V+1];
        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 시작점
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if(v[cur.v]) continue;
            v[cur.v] = true;

            for(int i=1; i<=V; i++) {
                if(!v[i] && adjMatrix[cur.v][i]!=0 && dist[i] > cur.totalDistance + adjMatrix[cur.v][i]) {
                    dist[i] = cur.totalDistance + adjMatrix[cur.v][i];
                    pq.offer(new Node(i, dist[i]));
                }
            }
        }

        // 출력
        for(int i=1; i<=V; i++) {
            System.out.println(dist[i]==Integer.MAX_VALUE ? "INF" : dist[i]);
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

