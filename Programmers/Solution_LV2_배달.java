import java.util.*;

class Solution_LV2_배달 {
    static int N, K;
    static ArrayList<Edge>[] adj;
    static class Edge implements Comparable<Edge> {
        int v, w;
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
        
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
    
    public int solution(int n, int[][] road, int k) {
        N = n;
        K = k;
        adj = new ArrayList[N+1]; // 1번 마을부터
        for(int i=1;i<=N; i++) {
            adj[i] = new ArrayList<Edge>();
        }
        
        for(int[] r: road) {
            int from = r[0];
            int to = r[1];
            int w = r[2];
            adj[from].add(new Edge(to, w));
            adj[to].add(new Edge(from, w));
        }
        
        int cnt = dikstra();
        return cnt;
    }
    
    static int dikstra() {
        int cnt = 0;
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.offer(new Edge(1, 0));
        dist[1] = 0;
        boolean[] v = new boolean[N+1];
        
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(v[cur.v]) continue;
            v[cur.v] = true;
            // cur과 연결된 정점들 확인
            for(Edge next: adj[cur.v]) {
                if(!v[next.v] && dist[next.v] > cur.w + next.w) {
                    dist[next.v] = cur.w + next.w;
                    pq.offer(new Edge(next.v, dist[next.v]));
                }
            } // 
        }
        for(int i=1; i<=N; i++) {
            if(dist[i] <= K) ++cnt;
        }
        return cnt;
    }
    
}