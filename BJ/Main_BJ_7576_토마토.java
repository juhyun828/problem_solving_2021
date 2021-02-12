import java.io.*;
import java.util.*;

// 210212
public class Main_BJ_7576_토마토 {
	
	static int M, N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] days;
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	static Queue<Integer[]> q;
	
	static int check() {
		int max = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if (map[r][c]==0)
					return -1;
				else if (map[r][c]==1) {
					max = Math.max(max, days[r][c]);
				}
			}
		}
		return max;
	}
	
	static void bfs() {
		
		while (!q.isEmpty()) {
			Integer[] v = q.poll();
			for(int d=0; d<4; d++) {
				int nr = v[0] + dr[d];
				int nc = v[1] + dc[d];
				
				if (nr>=0 && nr<N && nc>=0 && nc<M 
						&& map[nr][nc]==0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					map[nr][nc] = 1;
					days[nr][nc] = days[v[0]][v[1]] + 1;
					q.offer(new Integer[] {nr, nc});
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_7576_토마토.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		 
		M = Integer.parseInt(st.nextToken()); 
		N = Integer.parseInt(st.nextToken()); 
		
		map =  new int[N][M];
		days = new int[N][M];
		visited = new boolean[N][M];
		q = new ArrayDeque<Integer[]>();
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()); 
				if (map[r][c]==1) {
					q.offer(new Integer[] {r, c});
					visited[r][c] = true;
				}
			}
		} 
		
		bfs();
		System.out.println(check());
		
		br.close(); 
	} // main
}
