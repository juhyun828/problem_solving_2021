import java.io.*;
import java.util.*;
// 210403

public class Main_BJ_1261_알고스팟_pq {
	static int M, N, min;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_BJ_1261_알고스팟.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = stoi(st.nextToken()); // 가로
		N = stoi(st.nextToken()); // 세로
		min = Integer.MAX_VALUE;
		
		map = new int[N+1][M+1];
		
		char[] chArr;
		for(int i=1; i<=N; i++) {
			chArr = br.readLine().toCharArray();
			for(int j=1; j<=M; j++) {
				map[i][j] = chArr[j-1]-'0';
			}
		}
		
		bfs(new Pos(1, 1, 0));
		System.out.println(min);
		
		br.close();
	}
	
	static int bfs(Pos start) {
		Queue<Pos> pq = new PriorityQueue<>();
		boolean[][] v = new boolean[N+1][M+1];
		
		// 시작점
		pq.offer(start);
		v[start.r][start.c] = true;
		
		Pos cur;
		int nr, nc;
		
		while(!pq.isEmpty()) {
			cur = pq.poll();
			
			if(cur.r == N && cur.c == M) {
				min = Math.min(min, cur.cnt);
				return min;
			}
			
			for(int d=0; d<4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				
				// 1. 범위 체크 2. 방문 여부 체크
				if(nr<1 || nr>N || nc<1 || nc>M || v[nr][nc]) continue;
				
				// 3. 벽인지 체크
				if(map[nr][nc]==1) {
					pq.offer(new Pos(nr, nc, cur.cnt+1));
					v[nr][nc] = true;
				} else {
					pq.offer(new Pos(nr, nc, cur.cnt));
					v[nr][nc] = true;
				}
			}

		}

		return min;

	}

	static class Pos implements Comparable<Pos>{
		int r, c, cnt;
		public Pos(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.cnt, o.cnt);
		}

	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
