import java.io.*;
import java.util.*;

// 210213

public class Main_BJ_2178_미로탐색 {
	static int N, M; 
	static int[][] map;
	static int[][] dist;
	static boolean[][] visited;
	static Queue<Integer[]> q;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static void bfs() {
		// 어차피 큐의 앞은 0, 0
		while (!q.isEmpty()) {
			Integer[] v = q.poll();
			int r = v[0];
			int c = v[1];
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M && 
					map[nr][nc]==1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new Integer[] {nr, nc});
					if (nr==N-1&&nc==M-1) {
						dist[nr][nc] = Math.min(dist[nr][nc], dist[r][c] + 1);
					} else {
						dist[nr][nc] = dist[r][c] + 1;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; 
		q = new ArrayDeque<Integer[]>();
		q.offer(new Integer[] {0, 0});
		
		visited = new boolean[N][M];
		dist = new int[N][M];
		
		for(int r=0; r<N; r++) {
			String[] str = br.readLine().split("");
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(str[c]);
				if (map[r][c]==1) {
				}
			}
		}
		
		visited[0][0] = true;
		dist[0][0] = 1;
		dist[N-1][M-1] = Integer.MAX_VALUE;
		bfs();
		System.out.println(dist[N-1][M-1]);
		br.close();
	}
}
