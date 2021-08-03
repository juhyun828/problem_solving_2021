import java.io.*;
import java.util.*;
// 210702

public class Main_BJ_1922_네트워크연결 {
    static int N, M;
    static int[] parents;
    static PriorityQueue<Edge> pq;

    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        M = stoi(br.readLine());
        parents = new int[N+1];
        for(int i=1; i<=N; i++) {
            parents[i] = i;
        }

        pq = new PriorityQueue<Edge>();
        for(int i=0; i<M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            pq.offer(new Edge(from, to, weight));
        }

        int total = kruskal();
        System.out.println(total);

        br.close();
    }

    static int kruskal() {
        int total = 0;

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            int xRoot = find(cur.from);
            int yRoot = find(cur.to);
            if(xRoot!=yRoot) { // 사이클이 발생하지 않을 시에만
                union(xRoot, yRoot);
                total += cur.weight;
            }
        }
        return total;
    }

    static int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) {
            if(x < y) {
                parents[y] = x;
            } else {
                parents[x] = y;
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}