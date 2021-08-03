import java.io.*;
import java.util.*;
// 210422

public class Main_BJ_2636_치즈 {
	static int H, W, cheeseCnt; // 세로, 가로
	static int[][] map; // 0: 빈칸, 1: 치즈
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_2636_치즈.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		H = stoi(st.nextToken());
		W = stoi(st.nextToken());
		map = new int[H][W];
		cheeseCnt = 0;
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<W; j++) {
				map[i][j] = stoi(st.nextToken());
				if(map[i][j]==1) ++cheeseCnt;
			}
		}
		
		solve();
		br.close();
	}
	
	static void solve() {
		int lastCnt = 0;
		int time = 0;
		
		while(true) {
			lastCnt = cheeseCnt;
			++time;
			bfs();
			if(cheeseCnt==0) break;
		}
		
		System.out.println(time);
		System.out.println(lastCnt);
	}
	
	static void bfs(){
		// 공기를 만날 때 마다 녹여야 하기 때문에, 공기 칸인 0, 0에서 시작
		// 공기 칸에서 시작하며 만나는 빈칸은 구멍이 아니라 공기다.
		
		boolean[][] v = new boolean[H][W];
		ArrayDeque<Pos> q = new ArrayDeque<>();
		v[0][0] = true;
		q.offer(new Pos(0, 0));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
		
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr<0 || nr>=H || nc<0 || nc>=W || v[nr][nc]) continue;
				if(map[nr][nc]==0) { // 만나게 되는 공기는 다음 탐색 지점
					v[nr][nc] = true;
					q.offer(new Pos(nr, nc));
					
				} else if(map[nr][nc]==1) { // 치즈를 만나면
					v[nr][nc] = true;
					map[nr][nc] = 0;
					--cheeseCnt;
				}
			}

		}

	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}