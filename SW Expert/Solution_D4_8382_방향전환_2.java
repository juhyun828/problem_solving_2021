import java.io.*;
import java.util.*;
// 210322

public class Solution_D4_8382_방향전환_2 {
	static int min;
	static int x1, y1, x2, y2;

	static int stoi(String str) {
		return Integer.parseInt(str);
	}

	static class Data {
		int x, y;
		int cnt; // 몇번만에 움직였는지
		int dir;
		
		public Data(int x, int y, int cnt, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}	
	}
	
	// dfs(x, y, cnt, dir) -> dfs는 보통 parameter로 들고 다님
	static int[] dx = {0, 0, -1, 1}; // 상하 좌우
	static int[] dy = {-1, 1, 0, 0}; // 세로 가로
	
	static void bfs() {
		Queue<Data> q = new ArrayDeque<>();
		boolean[][][] v = new boolean[201][201][2]; // -100 ~ 100, (0,0)도 포함
													// 3차원 방문 체크, 0 가로, 1 세로
		
		q.offer(new Data(x1, y1, 0, 0)); // 시작점, 맨 처음은 0으로 센다, dir = 0 가로, 1 세로
		v[y1][x1][0] = true; // y가 세로 방향인거 주의하기
		q.offer(new Data(x1, y1, 0, 1)); // dir = 0 가로, 1 세로 -> 첫 이동은 어떤 이동이든 상관x (가로, 세로 둘 다 시
		v[y1][x1][1] = true;
		
		Data cur = null;
		int nx, ny;
		while(!q.isEmpty()) {
			cur = q.poll(); // x2, y2에 도착해야 함
			if(cur.x == x2 && cur.y == y2) { // 나중에 와도 이거보다 최단 거리일 수 x
				min = cur.cnt; // 현재 몇번만에 왔는지
				return;
			}
			
			// 가로 -> 세로
			if(cur.dir == 0) {
				for(int d=0; d<2; d++) {
					nx = cur.x + dx[d];
					ny = cur.y + dy[d];
					// 범위 체크
					if(nx<0 || nx>=201 || ny<0 || ny>=201) continue;
					// 방문 체크
					if(v[ny][nx][1]) continue;
					// 재방문 위해 큐에 삽입
					q.offer(new Data(nx, ny, cur.cnt+1, 1));
					v[ny][nx][1] = true;
				}
			}
			
			// 세로 -> 가로
			if(cur.dir == 1) {
				for(int d=2; d<4; d++) {
					nx = cur.x + dx[d];
					ny = cur.y + dy[d];
					// 범위 체크
					if(nx<0 || nx>=201 || ny<0 || ny>=201) continue;
					// 방문 체크
					if(v[ny][nx][0]) continue;
					// 재방문 위해 큐에 삽입
					q.offer(new Data(nx, ny, cur.cnt+1, 0));
					v[ny][nx][0] = true;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_D4_8382_방향전환.txt"));/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			st = new StringTokenizer(br.readLine().trim(), " ");
			min = Integer.MAX_VALUE;
			
			x1= stoi(st.nextToken()) + 100;  // 음수 -> 양수화
			y1 = stoi(st.nextToken()) + 100; 
			x2 = stoi(st.nextToken()) + 100; 
			y2 = stoi(st.nextToken())+ 100; 
			
			bfs();
			
			sb.append("#" + tc + " " + min + "\n");
		}
		System.out.println(sb.toString());
		
		br.close();
	} //
}