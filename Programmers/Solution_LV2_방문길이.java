// 210722
class Solution_LV2_방문길이 {
    public int solution(String dirs) {
        int answer = 0;
        
        boolean[][][][] v = new boolean[11][11][11][11];
        int sr = 5, sc = 5;
        
        for(char d: dirs.toCharArray()) {
            int nr = sr, nc = sc;
            
            if(d=='U') {
                nr -= 1;
                
            } else if(d=='D') {
                nr += 1;
                
            } else if(d=='R') {
                nc += 1;
                
            } else if(d=='L') {
                nc -= 1;
            }
            
            // 이동x, 원래 위치로
            if(nr<0 || nr>10 || nc<0 || nc>10) continue;
             
            if(!v[sr][sc][nr][nc]) {
                ++answer;
				// (sr, sc) -> (nr, nc)와, (nr, nc) -> (sr, sc)는 같은 길을 다른 방향에서 이동한 것이다.
                v[sr][sc][nr][nc] = true;
                v[nr][nc][sr][sc] = true;
            } 
            
            sr = nr;
            sc = nc; // 이동
        }
        
        return answer;
    }
}