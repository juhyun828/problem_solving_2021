import java.util.*;
// 211008

class Solution_LV2_9주차_전력망을둘로나누기 {
    static int answer;
    static boolean[] v;
    public int solution(int n, int[][] wires) {
        answer = Integer.MAX_VALUE;
        
        // 0~wires.length번 송전까지 끊는다.
        for(int i=0; i<wires.length; i++) {
            ArrayList<ArrayList<Integer>> adj = divide(n, i, wires);
            // 방문
            v = new boolean[n+1];
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int start=1; start<=n; start++) {
                if(!v[start]) {
                    int cnt = bfs(adj, start);
                    list.add(cnt);
                }
            }
            answer = Math.min(answer, Math.abs(list.get(0)-list.get(1)));
        }
        
        return answer;
    }
    
    static ArrayList<ArrayList<Integer>> divide(int n, int removed, int[][] wires) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<=n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        
        for(int i=0; i<wires.length; i++) {
            if(removed==i) continue;
            int u = wires[i][0];
            int v = wires[i][1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return adj;
    }
    
    static int bfs(ArrayList<ArrayList<Integer>> adj, int start) {
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(start);
        v[start] = true;
        int cnt = 1;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(int next: adj.get(cur)) {
                if(!v[next]) {
                    q.offer(next);
                    v[next] = true;;
                    ++cnt;
                }
            }
        }
        return cnt; // 연결된 송전탑
    }
}