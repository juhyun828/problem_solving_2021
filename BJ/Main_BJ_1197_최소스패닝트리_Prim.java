import java.io.*;
import java.util.*;
// 210628

public class Main_BJ_1197_최소스패닝트리_Prim {
    // 프림 알고리즘 -> 정점 중심
    static int V, E;
    static ArrayList<Node>[] adj;
    static class Node {
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        adj = new ArrayList[V+1];
        for(int i=1; i<=V; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            adj[from].add(new Node(to, weight));
            adj[to].add(new Node(from, weight));
        }

        int ans = kruskal();
        System.out.println(ans);
        br.close();
    }

    static int kruskal() {
        int[] minEdge = new int[V+1];
        Arrays.fill(minEdge, Integer.MAX_VALUE); // i번째 정점까지 가는 최소 비용
        boolean[] v = new boolean[V+1];
        // 임의의 정점을 시작지점으로 지정
        minEdge[1] = 0;
        int totalWeight = 0;

        for(int c=0; c<V; c++) { // 모든 정점 택
            int min = Integer.MAX_VALUE;
            int minVertex = 0;
            for(int i=1; i<=V; i++) {
                if(!v[i] && min > minEdge[i]) {
                    // 아직 방문x 최소 비용으로 방문 가능한 정점 찾기
                    min = minEdge[i];
                    minVertex = i;
                }
            }

            totalWeight += min; // 최소 비용으로 방문하기
            v[minVertex] = true;

            // minVertext를 거쳐서 방문할 수 있는 정점들을 최소 비용 update
            for(Node node: adj[minVertex]) {
                if(!v[node.to] && minEdge[node.to] > node.w) {
                    minEdge[node.to] = node.w;
                }
            }
        }
        return totalWeight;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
