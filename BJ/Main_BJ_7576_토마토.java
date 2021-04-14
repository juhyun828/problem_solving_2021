import java.io.*;
import java.util.*;
// 210414

public class Main_BJ_7576_토마토 {
	static int M, N;
	static int[][] map;
	static Queue<Tomato> q;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Tomato {
		int r, c;

		public Tomato(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
	
	static void bfs() {
		Tomato cur;
		int nr, nc;
		
		while (!q.isEmpty()) {
			cur = q.poll();
			
			for(int d=0; d<4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
				if(map[nr][nc]==-1 || map[nr][nc]>0) continue;
				map[nr][nc] = map[cur.r][cur.c] + 1;
				q.offer(new Tomato(nr, nc));
			}
		}
	}
	
	static int check() {
		int max = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) return -1;
				max = Math.max(max, map[i][j]);
			}
		}
		return max-1;
	}
	

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_7576_토마토.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = stoi(st.nextToken()); // 가로
		N = stoi(st.nextToken()); // 세로
		
		map = new int[N][M];
		q = new ArrayDeque<Tomato>();
		 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = stoi(st.nextToken());
				if(map[i][j]==1) 
					q.offer(new Tomato(i, j));
			}
		}
		
		bfs();
		System.out.println(check());

		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
