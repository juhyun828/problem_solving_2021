import java.util.*;
// 210507

public class Solution_LV3_경주로건설_dfs_fail {
	//상 하 좌 우
	// 세로 1 가로 -1
	static int[] dr = new int[] {-1, 1, 0, 0};
	static int[] dc = new int[] {0, 0, -1, 1};
	static boolean[][] v;
	static int N, min;
	
	public int solution(int[][] board) {
        N = board.length;
        min = Integer.MAX_VALUE;
        // 출발 0, 0 도착 N-1, N-1;
        
        v = new boolean[N][N];
        v[0][0] = true;
        
        for(int d=0; d<4; d++) {
			int nr = 0 + dr[d];
			int nc = 0 + dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
			if(board[nr][nc]==1) continue;
			v[nr][nc] = true;
			if(d==0 || d==1) 
				dfs(board, nr, nc, 1, 100);
			else
				dfs(board, nr, nc, -1, 100);
			v[nr][nc] = false;
        }
        
        return min;
    }

	static void dfs(int[][] board, int r, int c, int dir, int cost) {
		if(cost>min) return;
		if(r==N-1 && c==N-1) {
			min = Math.min(min, cost);
			return;
		} 
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
			if(board[nr][nc]==1 || v[nr][nc]) continue;
			v[nr][nc] = true;
			
			if(dir==1) {
				if(d==0 || d==1)  // 같은 방향
					dfs(board, nr, nc, 1, cost+100);
				else 
					dfs(board, nr, nc, -1, cost+100+500);
				
			} else if(dir==-1) {
				if(d==2 || d==3)
					dfs(board, nr, nc, -1, cost+100);
				else
					dfs(board, nr, nc, 1, cost+100+500);
			}
			v[nr][nc] = false;
			
		}
		
	}
}
