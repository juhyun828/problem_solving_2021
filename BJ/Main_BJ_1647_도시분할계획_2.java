import java.io.*;
import java.util.*;
// 210815

public class Main_BJ_1647_도시분할계획_2 {
    static int V, E;
    static int[] parents;
    static ArrayList<Edge> edges; // 간선 그래프

    static class Edge implements Comparable<Edge> {
        int nodeA;
        int nodeB;
        int distance;

        public Edge(int nodeA, int nodeB, int distance) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.distance = distance;
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

        parents = new int[V+1];
        edges = new ArrayList<Edge>();
        int res = 0;

        for(int i=1; i<=V; i++){
            parents[i] = i;
        }

         // 간선 정보 입력
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            int cost = stoi(st.nextToken());
            edges.add(new Edge(a, b, cost));
        }

        // 간선 비용 순 정렬
        Collections.sort(edges);
        int max = 0; // MST에 포함되는 가장 비용이 큰 간선

        // 간선 하나씩 확인하며 사이클이 발생하지 않는 경우만 집합에 포함
        for(int i=0; i<E; i++) {
            int cost = edges.get(i).distance;
            int a = edges.get(i).nodeA;
            int b = edges.get(i).nodeB;

            a = find(a);
            b = find(b);

            if(a != b) {
                union(a, b);
                res += cost;
                max = cost;
            }
        }

        System.out.println(res - max);
        br.close();
    }

    static int find(int x) {
        if(parents[x]==x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x < y) parents[y] = x;
        else if(x > y) parents[x] = y;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
