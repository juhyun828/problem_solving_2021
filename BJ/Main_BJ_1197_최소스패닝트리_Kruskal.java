import java.io.*;
import java.util.*;
// 210622

public class Main_BJ_1197_최소스패닝트리_Kruskal {
    // 간선 중심 - 크루스칼 알고리즘
    // 가중치 낮은 순서대로 정점 - 1개의 간선 택
    static int V, E;
    static PriorityQueue<Edge> pq;
    static int[] parents;
    static class Edge implements Comparable<Edge>{
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        pq = new PriorityQueue();
        parents = new int[V+1];
        for(int i=1; i<=V; i++) {
            parents[i] = i;
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            pq.offer(new Edge(from, to, weight));
        }

        kruskal();
        br.close();
    }

    static void kruskal() {
        int totalWeight = 0;

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(union(cur.from, cur.to)) { // 합집합 가능 : 사이클 발생x
                totalWeight += cur.weight;
            }
        }

        System.out.println(totalWeight);
    }

    // 1. 부모 루트 찾기
    static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    // 2. 합집합 연산이 불가하면 사이클 발생
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot==bRoot) return false;
        // a집합에 b를 포함시킨다.
        parents[bRoot] = aRoot;
        return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

