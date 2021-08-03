import java.util.*;
// 210629

class Solution_LV3_가장먼노드 {
    static ArrayList<Integer>[] adj;
    static int[] dist;
    static int max;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        adj = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        dist = new int[n+1];
        // 간선 정보 입력
        for(int[] e: edge) {
            int from = e[0];
            int to = e[1];
            adj[from].add(to);
            adj[to].add(from);
        }

        max = 0;
        bfs();
        for(int i=1; i<=n; i++) {
            if(dist[i]==max) ++answer;
        }
        return answer;
    }
    
    static void bfs() {
        Queue<Integer> q = new ArrayDeque();
        // 시작점
        q.offer(1);
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            // 연결된 부분들 방문
            for(int next: adj[cur]) {
                if(next==1) continue; // 시작점
                if(dist[next]==0) {
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                    max = Math.max(max, dist[next]);
                }
            }
        }
    }
}