import java.io.*;
import java.util.*;
// 210625

public class Main_BJ_1238_파티_list {
    static int N, M, X;
    static ArrayList<Node>[] list, reverseList;
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
        distFrom = new int[N+1]; // 각 지점 -> X 지점 최단거리
        distTo = new int[N+1];  // 각 지점 <- X 지점 최단거리
        Arrays.fill(distFrom, Integer.MAX_VALUE);
        Arrays.fill(distTo, Integer.MAX_VALUE);

        list = new ArrayList[N+1];
        reverseList = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<Node>();
            reverseList[i] = new ArrayList<Node>();
        }

         // 간선 정보 입력
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int w = stoi(st.nextToken());
            list[from].add(new Node(to, w));
            reverseList[to].add(new Node(from, w));
        }

        dijkstra(list, distFrom);
        dijkstra(reverseList, distTo);

        int max = 0;
        for(int i=1; i<=N; i++) {
            max = Math.max(max, distFrom[i] + distTo[i]);
        }

        System.out.println(max);

        br.close();
    }

    static void dijkstra(ArrayList<Node>[] list, int[] dist) {
        dist[X] = 0; // 시작점
        boolean[] v = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(X, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(v[cur.v]) continue;

            v[cur.v] = true;
            // cur을 경유하며 최단 거리를 찾는다.
            for(Node node: list[cur.v]) {
                if(dist[node.v] > cur.w + node.w) {
                    dist[node.v] = cur.w + node.w;
                    pq.offer(new Node(node.v, dist[node.v]));
                }
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
