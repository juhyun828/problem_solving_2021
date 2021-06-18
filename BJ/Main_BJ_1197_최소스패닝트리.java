import java.io.*;
import java.util.*;
// 210618

public class Main_BJ_1197_최소스패닝트리 {
    static int V, E; // 정점, 간선
    static ArrayList<Edge>[] adj;

    static class Edge {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        adj = new ArrayList[V+1];
        for(int i=0; i<=V; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            adj[from].add(new Edge(to, weight));
            adj[to].add(new Edge(from, weight));
        }

        prim();
        br.close();
    }

    static void prim() {
        boolean[] v = new boolean[V+1];
        int[] minEdge = new int[V+1];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        // 시작점
        minEdge[1] = 0;
        int totalWeight = 0;

        for(int c=0; c<V; c++) { // 정점 수만큼 만복
            int min = Integer.MAX_VALUE;
            int minVertex = 0;

            for(int i=1; i<=V; i++) {
                if(!v[i] && min > minEdge[i]) {
                    min = minEdge[i];
                    minVertex = i;
                }
            }

            // 방문
            v[minVertex] = true;
            totalWeight += min;

            for(Edge edge: adj[minVertex]) {
                if(!v[edge.v] && minEdge[edge.v] > edge.w) {
                    minEdge[edge.v] = edge.w;
                }
            }
        }

        System.out.println(totalWeight);
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

