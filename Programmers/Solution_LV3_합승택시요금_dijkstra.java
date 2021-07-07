import java.util.*;
// 210707

class Solution_LV3_합승택시요금_dijkstra {
    static List<Node>[] adj;
    static class Node implements Comparable<Node> {
        int v, w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
	public int solution(int n, int s, int a, int b, int[][] fares) {   
        int min = Integer.MAX_VALUE;
        adj = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            adj[i] = new ArrayList<Node>();
        }
        // 간선 정보 입력
        for(int[] fare: fares) {
            int from = fare[0];
            int to = fare[1];
            int w = fare[2];
            adj[from].add(new Node(to, w));
            adj[to].add(new Node(from, w));
        }
        int[] distStart = dijkstra(s, n);
        int[] distA = dijkstra(a, n);        
        int[] distB = dijkstra(b, n);
        
        for(int i=1; i<=n; i++) {
            // 시작점에서i까지 + A에서 i + B에서 i까지 가는 비용의 최소값
            int cost = distStart[i] + distA[i] + distB[i];  
            min = Math.min(min, cost);
        }
        
        return min;
    }
    
    static int[] dijkstra(int startNode, int n) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        // 시작 정점 
        dist[startNode] = 0;
        pq.offer(new Node(startNode, 0));
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(dist[cur.v] < cur.w) continue;
            
            for(Node next: adj[cur.v]) {
                if(dist[next.v] > cur.w + next.w) {
                    dist[next.v] = cur.w + next.w;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
        
        return dist;
    }
}