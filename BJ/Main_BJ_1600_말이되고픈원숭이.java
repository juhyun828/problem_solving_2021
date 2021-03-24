import java.io.*;
import java.util.*;
// 210324

public class Main_BJ_1600_말이되고픈원숭이 {
	static int K, W, H, min;
	static int[][] map;
	static int[] dr1 = {-1, -2, -2, -1, 1, 2, 2, 1}; // 말 방식
	static int[] dc1 = {-2, -1, 1, 2, -2, -1, 1, 2};
	static int[] dr2 = {0, -1, 0, 1}; // 원숭이 방식
	static int[] dc2 = {-1, 0, 1, 0};

	static class Pos {
		int r, c, kCnt, dist;

		public Pos(int r, int c, int kCnt, int dist) {
			this.r = r;
			this.c = c;
			this.kCnt = kCnt;
			this.dist = dist;
		}
		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + ", kCnt=" + kCnt + ", dist=" + dist + "]";
		}
	}
	
	static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][][] v = new boolean[H][W][K+1]; 
		
		// 시작 위치
		q.offer(new Pos(0, 0, 0, 0)); 
		Arrays.fill(v[0][0], true);
		
		Pos cur;
		int nr, nc;
		
		while(!q.isEmpty()) {
			cur = q.poll();
			if(cur.r == (H-1) && cur.c == (W-1)) {
				// min 갱신				
				System.out.println(cur);
				min = Math.min(min, cur.dist);
				continue;
			}
			
			// 말 움직임
			if(cur.kCnt < K) { // 말로 움직인 횟수가 아직 남아있음
				for(int d=0; d<8; d++) {
					nr = cur.r + dr1[d];
					nc = cur.c + dc1[d];
					if(nr<0 || nr>=H || nc<0 || nc>=W || v[nr][nc][cur.kCnt+1]) continue;
					if(map[nr][nc]==1) continue; 
					q.offer(new Pos(nr, nc, cur.kCnt+1, cur.dist+1));
					v[nr][nc][cur.kCnt+1] = true;
				}
			}
			
			// 원숭이 
			for(int d=0; d<4; d++) {
				nr = cur.r + dr2[d];
				nc = cur.c + dc2[d];
				if(nr<0 || nr>=H || nc<0 || nc>=W || v[nr][nc][cur.kCnt]) continue;
				if(map[nr][nc]==1) continue; 
				q.offer(new Pos(nr, nc, cur.kCnt, cur.dist+1));
				v[nr][nc][cur.kCnt] = true;
			}
			
		} // while
		
	}
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_BJ_1600_말이되고픈원숭이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		K = stoi(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		W = stoi(st.nextToken());
		H = stoi(st.nextToken());
		min = Integer.MAX_VALUE;
		
		map = new int[H][W];
		for(int r=0; r<H; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<W; c++) {
				map[r][c] = stoi(st.nextToken());
			}
		}
		
		bfs();
		System.out.println((min==Integer.MAX_VALUE ? -1 : min));
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
