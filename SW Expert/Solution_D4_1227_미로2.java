import java.io.*;
import java.util.*;
// 210315

public class Solution_D4_1227_미로2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int res = 0;
		int[][] map;
		int sx=0, sy=0, ex=0, ey=0; // 시작 2, 도착 3
		
		for(int tc=1; tc<=tc; tc++) {
			res = 0;
			map = new int[100][100];
			int[] dx = {0, 0, -1, 1}; // 상하좌우
			int[] dy = {-1, 1, 0, 0};
			
			
			for(int i=0; i<100; i++) {
				String s = sc.next();
				for(int j=0; j<100; j++) {
					map[i][j] = s.charAt(j) -'0';// '1' '0' - '0' char -> int
					if(map[i][j]==2) {// 츨빌점
						sx=j;
						sy=i; // y가 세로
						map[i][j]=1; // 출발지로 다시 오지 않으니 아예 벽으로 처리해놓기
					} else if(map[i][j]==3) {
						ex=j;
						ey=i;
					}
				}
			}
			// 스타트 => 끝까지 갈 수 있다, 4방위 탐색
			boolean[][] v = new boolean[100][100];
			Queue<Data> q = new LinkedList<>();
			
			q.offer(new Data(sx, sy)); // 시작점
			v[sy][sx] = true;
			 // bfs - 큐 & 방문체크 한쌍
			
			Data cur; 
			int nx, ny;
			while(!q.isEmpty()) {
				cur = q.poll();
				// 현재 위치가 도착지점인지 판단
				if(cur.x==ex && cur.y==ey) {
					res = 1;
					break;
				}
				
				for( int d=0; d<4; d++) {
					// 4방 범위 체크
					// 벽이면 안감 - 가지치기 
					nx = cur.x + dx[d];
					ny = cur.y + dy[d];
					if(map[ny][nx]==1) {
						continue;
					}			
					if(v[ny][nx]) {
						continue;
					}
					q.offer(new Data(nx, ny));
					v[ny][nx] = true;
				}
			}
			
			System.out.println("#" + tc + " " + res);
		}
	}
	
	static class Data{
		int x, y;

		public Data(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}  // class

}
