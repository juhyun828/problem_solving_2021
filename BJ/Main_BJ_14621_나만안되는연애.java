import java.io.*;
import java.util.*;
// 211022

public class Main_BJ_14621_나만안되는연애 {
    static int N, M;
    static PriorityQueue<Edge> pq;
    static char[] gender;
    static int[] parents;
    static class Edge implements Comparable<Edge> {
        int from, to, w;
        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.w, edge.w);
        }
    }
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        parents = new int[N+1];
        for(int i=1; i<=N; i++) {
            parents[i] = i;
        }

        gender = new char[N+1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++) {
            gender[i] = st.nextToken().charAt(0);
        }

        pq = new PriorityQueue<Edge>();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = stoi(st.nextToken());
            int v = stoi(st.nextToken());
            int w = stoi(st.nextToken());
            if(gender[u]!=gender[v])
                pq.offer(new Edge(u, v, w));
        }

        System.out.println(kruskal());
        br.close();
    }

    static int kruskal() {
        // 사이클이 발생하지 않는 간선을 비용이 작은 순으로 N-1개 택한다.
        int total = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(find(cur.from)!=find(cur.to)) {
                total += cur.w;
                union(cur.from, cur.to);
                ++cnt;
            }

            if(cnt==N-1) break;
        }
        if(cnt<N-1) return -1;
        else return total;
    }

    static int find(int x) {
        if(parents[x]==x) return x;
        else return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x!=y) {
            parents[y] = x;
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}