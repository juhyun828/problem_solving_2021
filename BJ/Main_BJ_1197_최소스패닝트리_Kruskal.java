import java.io.*;
import java.util.*;
// 210628

public class Main_BJ_1197_최소스패닝트리_Kruskal {
    // 간선 중심 - 크루스칼 알고리즘 -> 가중치 낮은 순서대로 V-1개의 간선을 택한다.
    static int V, E;
    static int[] parents;
    static PriorityQueue<Edge> pq;

    static class Edge implements Comparable<Edge>{
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        pq = new PriorityQueue<Edge>();
        parents = new int[V+1];
        for(int i=1; i<=V; i++) {
            parents[i] = i;
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            pq.offer(new Edge(from, to, weight));
        }

        int ans = kruskal();
        System.out.println(ans);
        br.close();
    }

    static int kruskal() {
        // 사이클이 발생하지 않는 V-1개의 간선 구하기
        int totalWeight = 0;
        int cnt = 0;
        while (true) {
            if(cnt==V-1) break;
            //if(pq.isEmpty()) break;
            Edge cur = pq.poll();
            if(union(cur.from, cur.to)) { // 합집합이 가능-> 사이클x
                totalWeight += cur.w;
                ++cnt;
            }
        }
        return totalWeight;
    }

    // 1. 부모 루트 찾기
    static int find(int a) {
        if(parents[a]==a) return a;
        return parents[a] = find(parents[a]);
    }

    // 2. 합집합 연산이 불가하면 사이클 발생
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot==bRoot) return false;
        parents[aRoot] = bRoot;
        return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

