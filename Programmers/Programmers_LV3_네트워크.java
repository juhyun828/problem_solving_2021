class Solution {
    static boolean[] v;
    
    static int check(int n) {
        for(int i=0; i<n; i++) {
            if(!v[i]) return i;
        }
        return -1;
    }
    
    static void dfs(int vertext, int n, int[][] computers) {
        if(vertext==n) return;
        for(int i=0; i<n; i++) {
            if(computers[vertext][i]==0 || v[i]) continue;
            v[i] = true;
            dfs(i, n, computers);
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = -1;

        v = new boolean[n];
        
        while(true) {
            ++answer;
            int res = check(n); 
            if(res==-1) break;
            dfs(res, n, computers);
        }
        
        return answer;
    }
}