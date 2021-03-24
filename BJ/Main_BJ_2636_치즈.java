import java.io.*;
import java.util.*;
// 210324

public class Main_BJ_2636_치즈 {
	static int H, W, time, cheeseCnt;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void bfs() {
		
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] v = new boolean[H][W];
		
		q.offer(new Pos(0, 0));
		v[0][0] = true;
		
		Pos cur = null;
		int nr, nc;
 		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int d=0; d<4; d++) {
				nr = cur.r + dr[d]; 
				nc = cur.c + dc[d];
				if(nr<0 || nr>=H || nc<0 || nc>=W || v[nr][nc]) continue;
				if(map[nr][nc]==1) { // 공기가 치즈와 만나면 녹임
					map[nr][nc] = 0; 
					--cheeseCnt;
					v[nr][nc] = true; // 치즈를 녹인 곳도 방문처리 해야 다음 치즈가 공기로 인식 x
				} 
				else {
					q.offer(new Pos(nr, nc)); // 치즈가 아니라 공기
					v[nr][nc] = true;
				}
			}
		}	
	}
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_BJ_2636_치즈.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		H = stoi(st.nextToken());
		W = stoi(st.nextToken());
		map = new int[H][W];
		time = 0; cheeseCnt = 0;
		
		for(int r=0; r<H; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<W; c++) {
				map[r][c] = stoi(st.nextToken());
				if(map[r][c]==1) { // 치즈
					++cheeseCnt;
				}
			}
		}
		
		int lastCnt = 0;
		while(cheeseCnt>0) {
			++time;
			lastCnt = cheeseCnt;
			bfs();
			System.out.println(time + "\n" + lastCnt);
			printMap();
		}
		
		System.out.println(time + "\n" + lastCnt);
		br.close();
	}

}
