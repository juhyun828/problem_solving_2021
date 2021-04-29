import java.util.*;

class Solution_LV3_가장먼노드 {
    static ArrayList<Integer>[] adjList;
    static int[] v;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        // 인접 리스트 만들기
        adjList = new ArrayList[n+1];
        v = new int[n+1];
        Arrays.fill(v, -1);
        
        for(int i=1; i<=n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        
        int from, to;
        for(int i=0; i<edge.length; i++) {
            from = edge[i][0]; to = edge[i][1];
            adjList[from].add(to);
            adjList[to].add(from);
        }  
     
        int max= bfs(1, n);
        for(int i=1; i<=n; i++) {
            if(v[i]==max) ++answer;
        }

        return answer;
    }
    
    static int bfs(int start, int n) {
        int max = 0;
        Queue<Integer> q = new ArrayDeque<Integer>();
        // 시작점 방문
        q.offer(start);
        v[start] = 0;
        
        int cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            for(Integer next: adjList[cur]) {
                if(v[next]>-1) continue;
                q.offer(next);
                v[next] = v[cur]+1;
                max = Math.max(max, v[next]);
            }
        }
        
        return max;
    }
}