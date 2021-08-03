// 210509
class Solution_LV2_행렬테두리회전하기 {
	
    public int[] solution(int rows, int cols, int[][] queries) {
        int[] answer = new int[queries.length];
        int[] dr = new int[] {1, 0, -1, 0}; //하우상좌
        int[] dc = new int[] {0, 1, 0, -1};
        
        int[][] map = new int[rows+1][cols+1];
        int num = 0;
        for(int i=1; i<=rows; i++){
            for(int j=1; j<=cols; j++){
                map[i][j] = ++num;
            }
            
        }
 
        // 회전
        int idx = 0;
        for(int[] q: queries) {
        	int r1 = q[0], c1 = q[1], r2 = q[2], c2 = q[3];
        	int or = r1, oc = c1, d=0;
        	int start = map[r1][c1];
        	int min = Integer.MAX_VALUE;
        	while(true) {
        		if(or==r1&&oc==(c1+1)) {
        			map[or][oc] = start;
        			min = Math.min(min, map[or][oc]);
        			break;
        		}
        		
        		int nr = or + dr[d];
        		int nc = oc + dc[d];
        		
        		if(nr<r1||nr>r2||nc<c1||nc>c2) {
        			++d;
        			continue;
        		}
        		map[or][oc] = map[nr][nc];
        		or = nr; oc = nc;
        		min = Math.min(min, map[or][oc]);
        	}
        	
        	answer[idx++] = min;
        }
       
        return answer;
    }
    
}