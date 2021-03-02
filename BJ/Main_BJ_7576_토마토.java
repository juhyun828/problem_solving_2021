import java.io.*;
import java.util.*;
// 0302

public class Main_BJ_7576_토마토 {
	static int N, M, map[][], dist[][];
	static Queue<Tomato> q;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Tomato {
		int r, c;

		public Tomato(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_7576_토마토.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		M = stoi(st.nextToken()); // 가로
		N = stoi(st.nextToken()); // 세로
		
		map = new int[N][M]; 
		dist = new int[N][M]; 
		
		for(int r=0; r<N; r++) {
			Arrays.fill(dist[r], -1);
		}
		
		q = new ArrayDeque();
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<M; c++) {
				map[r][c] = stoi(st.nextToken());
				if(map[r][c]==1) {
					q.add(new Tomato(r, c));
					dist[r][c] = 0;
				}
			}
		}
		
		bfs();
		System.out.println(check());
		br.close();
	} // main

	static void bfs() {
		while(!q.isEmpty()) {
			Tomato t = q.poll();
			int r = t.r, c = t.c;
			
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr<0||nr>=N||nc<0||nc>=M||map[nr][nc]!=0) continue;
				map[nr][nc] = 1;
				dist[nr][nc] = dist[r][c] + 1;
				q.offer(new Tomato(nr, nc));
			}
			
		}
	}
	
	static int check() {
		int max = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c]==0) return -1;
				max = Math.max(max, dist[r][c]);
			}
		}
		return max;
	}
}
