import java.io.*;
import java.util.*;
// 210705

public class Main_BJ_1197_최소스패닝트리_Kruskal {
    static int V, E; // 정점, 간선 수
    static int[] parents;
    static PriorityQueue<Edge> pq;
    static class Edge implements Comparable<Edge> {
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
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        parents = new int[V+1];
        for(int i=1; i<=V; i++) {
            parents[i] = i;
        }
        pq = new PriorityQueue<Edge>();

        // 간선 정보 입력
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int w = stoi(st.nextToken());
            pq.offer(new Edge(from, to, w));
        }

        int total = kruskal();
        System.out.println(total);

        br.close();
    }

    static int kruskal() {
        int total = 0;
        int cnt = 0;

        // 사이클이 발생하지 않는 정점-1개의 간선 택
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(find(cur.from) != find(cur.to)) {
                union(cur.from, cur.to);
                total += cur.w;
                ++cnt;
            }
            if(cnt == V-1) break;
        }

        return total;
    }

    static int find(int x){
        if(parents[x]==x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x!=y) {
            if(x < y) parents[y] = x;
            else parents[x] = y;
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}