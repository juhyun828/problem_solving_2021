import java.io.*;
import java.util.*;

// 210212

public class Main_BJ_1012_유기농배추 {

	static int M, N, K;
	static int[][] map;
	static boolean[][] visited;
	static int cnt;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static Queue<Integer[]> q;
	
	static void printMap() {
		for(int r=0; r<M; r++) {
			for(int c=0; c<N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
	
	static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr>=0 && nr<M && nc>=0 && nc<N 
					&& map[nr][nc]==1 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
				
		}
	}
	
	static void bfs(int r, int c) {
		q.offer(new Integer[] {r, c});
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			Integer[] v = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = v[0] + dr[d];
				int nc = v[1] + dc[d];
				
				if (nr>=0 && nr<M && nc>=0 && nc<N 
						&& map[nr][nc]==1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new Integer[] {nr, nc});
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_1012_유기농배추.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // 가로 길이
			N = Integer.parseInt(st.nextToken()); // 세로 길이
			K = Integer.parseInt(st.nextToken()); // 배추 위치 개수
			
			map = new int[M][N];
			visited = new boolean[M][N];
			cnt = 0;
			q = new ArrayDeque<Integer[]>();
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int r = Integer.parseInt(st.nextToken()); 
				int c = Integer.parseInt(st.nextToken()); 
				map[r][c] = 1;
			}
			
			for(int r=0; r<M; r++) {
				for(int c=0; c<N; c++) {
					if (map[r][c]==0) {
						visited[r][c] = true;
					} else {
						if (!visited[r][c]) {
							++cnt;
							visited[r][c] = true;
							// dfs(r, c);
							bfs(r, c);
						}
					}
				}
			}

			System.out.println(cnt);
		} // for
		
		br.close();
	} // main
}
