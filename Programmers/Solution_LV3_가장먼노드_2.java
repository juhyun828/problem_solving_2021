import java.util.*;
// 210814

class Solution_LV3_가장먼노드_2 {
    static ArrayList<Integer>[] adj;
    static int max;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        adj = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        
        // 간선 정보 입력
        for(int[] e: edge) {
            int from = e[0];
            int to = e[1];
            adj[from].add(to);
            adj[to].add(from);
        }
        
        max = 0;
        int[] dist = bfs(n);
        for(int i=1; i<=n; i++) {
            if(max == dist[i]) ++answer;
        }
       
        return answer;
    }
    
    static int[] bfs(int n) {
        Queue<Integer> q = new ArrayDeque();
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        
        // 시작점
        q.offer(1); 
        dist[1] = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            // 연결된 부분들 방문
            for(int next: adj[cur]) {
                if(dist[next]==-1) { // 아직 방문x
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                    max = Math.max(max, dist[next]);
                }
            }
        }
        
        return dist;
    }
    
}