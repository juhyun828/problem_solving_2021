import java.io.*;
import java.util.*;
// 210531

// 두 개의 MST를 구해야한다.
// 크루스칼 알고리즘으로 전체 MST를 찾은 후, 비용이 기장 큰 간선을 제거한다.
public class Main_BJ_1647_도시분할계획 {
    static int V, E; // 집 (노드), 길 (간선) 양방향그래프, 가중치
    static int[] parent;
    static ArrayList<Edge> edges; // 간선 그래프
    static int res;

    static class Edge implements Comparable<Edge> {
        int distance;
        int nodeA;
        int nodeB;

        public Edge(int distance, int nodeA, int nodeB) {
            this.distance = distance;
            this.nodeA = nodeA;
            this.nodeB = nodeB;
        }

        @Override
        public int compareTo(Edge o) { // 거리비용 짧은거 순으로
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = stoi(st.nextToken()); // 노드 수
        E = stoi(st.nextToken()); // 간선 수
        parent = new int[V+1];
        edges = new ArrayList<Edge>();
        res = 0;

        for(int i=1; i<=V; i++){
            parent[i] = i;
        }

        // 모든 간선에 대한 정보 입력
        for(int j=0; j<E; j++){
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            int cost = stoi(st.nextToken());
            edges.add(new Edge(cost, a, b));
        }

        // 간선 비용 순 정렬
        Collections.sort(edges);
        int max = 0; // 최소 신장 트리에 포함되는 가장 비용이 큰 간선

        // 간선 하나씩 확인하며 사이클이 발생하지 않는 경우에만 집합에 포함
        for(int i=0; i<edges.size(); i++) {
            int cost = edges.get(i).distance;
            int a = edges.get(i).nodeA;
            int b = edges.get(i).nodeB;

            // 사이클 x
            if(findParent(a) != findParent(b)) {
                unionParent(a, b);
                res += cost;
                max = cost;
            }
        }

        System.out.println(res - max);
        br.close();
    }

    static int findParent(int x) {
        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }

    static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
