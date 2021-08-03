import java.io.*;
import java.util.*;
// 210412

public class Solution_D4_1249_보급로 {
	static int N;
	static int[][] map;
	// 시작 0,0 끝 N-1, N-1
	
	static class Pos implements Comparable<Pos>{
		int r, c, total;
		
		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.total, o.total);
		}

		public Pos(int r, int c, int total) {
			this.r = r;
			this.c = c;
			this.total = total;
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_D4_1249_보급로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			N = stoi(br.readLine());
			map = new int[N][N];
		
			for(int i=0; i<N; i++) {
				char[] c = br.readLine().toCharArray();
				for(int j=0; j<N; j++) {
					map[i][j] = c[j]-'0';
				}
			}
			
			sb.append("#" + tc +" " + bfs() + "\n");
		}
	
		System.out.println(sb.toString());
		br.close();
	}
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static int bfs() {
		
		// 1. v배열 초기값을 0으로 / 시작점 방문시 값을 1로 세팅 / v[nr][nc]==0이면 방문하지 않은 곳/ return min-1
		// 2. v배열 초기값을 -1으로 / 시작점 방문시 값을 0로 세팅 / v[nr][nc]==-1이면 방문하지 않은 곳/ return min1
		
		int[][] v = new int[N][N];
		for(int i=0; i<N; i++) {
			Arrays.fill(v[i], -1);
		}
		
		PriorityQueue<Pos> q = new PriorityQueue<Pos>();
		
		// 시작점
		v[0][0] = 0;
		q.offer(new Pos(0, 0, 0));
		
		Pos cur;
		int nr, nc;
		
		while(!q.isEmpty()) {			
			cur = q.poll();
			
			if(cur.r == (N-1) && cur.c == (N-1)) {
				return cur.total;
			}
			
			for(int d=0; d<4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;

				// 방문 안한 경우
				if(v[nr][nc]==-1) {
					v[nr][nc] = cur.total+map[nr][nc];
					q.offer(new Pos(nr, nc, cur.total+map[nr][nc]));
				}
				
				// 방문한 경우
				// 더 적은 total로 방문 가능한 경우만 방문
				else if(v[nr][nc]!=-1) {
					
					if(v[nr][nc] > cur.total+map[nr][nc]) {
						v[nr][nc] = cur.total+map[nr][nc];
						q.offer(new Pos(nr, nc, cur.total+map[nr][nc]));
					}
				}
			}
		}
		
		return 0;
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}

