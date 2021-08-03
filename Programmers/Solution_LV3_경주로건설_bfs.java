import java.util.*;
// 210507

public class Solution_LV3_경주로건설_bfs {
	//상 하 좌 우
	static int[] dr = new int[] {-1, 1, 0, 0};
	static int[] dc = new int[] {0, 0, -1, 1};
	static int N, min;
	static int[][] map;
	
	public int solution(int[][] board) {
        N = board.length;
        min = Integer.MAX_VALUE;
        // 출발 0, 0 도착 N-1, N-1;
        map = new int[N][N];
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		map[i][j] = board[i][j];
        	}
        }
        
        bfs();
        return min;
    }

	static class Pos {
		int r, c, dir, cost; // 세로 1 가로 -1
		
		public Pos(int r, int c,int dir, int cost) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cost = cost;
		}
		
	}
	
	static void bfs() {
		boolean[][] v = new boolean[N][N];
		Queue<Pos> q = new ArrayDeque<Pos>();
		
		// 시작점
		v[0][0] = true;
		map[0][0] = 1;
		q.offer(new Pos(0, 0, -3, 0));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if(cur.r==N-1&&cur.c==N-1) {
				min = Math.min(min, cur.cost);
				continue;
			}
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N || map[nr][nc]==1) continue;
				int newCost = cur.cost+100;
				int nd = cur.dir;
				
				if(cur.dir==-3) {
					if(d==0 || d==1) nd = -1;
					else nd = 1;
				} else if(cur.dir==-1 && (d==2 || d==3)) {
					newCost += 500;
					nd = 1;
				}
				else if(cur.dir==1 && (d==0 || d==1)) {
					 newCost += 500;
					 nd = -1;
				}
						
				if(!v[nr][nc]) { // 방문x
					map[nr][nc] = newCost;
					v[nr][nc] = true;
					q.offer(new Pos(nr, nc, nd, newCost));
					
				} else {
					if(map[nr][nc] >= newCost) { // 더 적은 비용으로 방문 가능
						map[nr][nc] = newCost;
						v[nr][nc] = true;
						q.offer(new Pos(nr, nc, nd, newCost));
					}
				}
			}

		}

	}
}
