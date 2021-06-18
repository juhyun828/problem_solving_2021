import java.io.*;
import java.util.*;
// 210617
// 인접리스트

public class Main_BJ_1753_최단경로_인접리스트 {
    static int V, E, start; // 정점, 간선 수, 시작 정점
    static ArrayList<Edge>[] adjList;

    static class Edge {
        int v, w; // 정점과 가중치

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "v=" + v +
                    ", w=" + w +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        start = stoi(br.readLine());
        adjList = new ArrayList[V+1];
        for(int i=0; i<=V; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++) { // 간선 정보 입력
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            adjList[from].add(new Edge(to, weight));
        }

        dijkstra();
        
        br.close();
    }

    static void dijkstra() {
        boolean[] v = new boolean[V+1];
        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 시작점
        dist[start] = 0;

        for(int c=0; c<V; c++) { // 정점 개수만큼 반복
            int min =Integer.MAX_VALUE;
            int minVertex = 0;
            for(int i=1; i<=V; i++) {
                if(!v[i] && min > dist[i]) {
                    min = dist[i];
                    minVertex = i;
                }
            }

            // 최소 비용 정점 방문
            v[minVertex] = true;
            // 최소 비용 정점과 인접한 정점들 거리 갱신
            for(Edge edge: adjList[minVertex]) {
                if(!v[edge.v] && dist[edge.v] > min + edge.w) {
                    dist[edge.v] = min + edge.w;
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

