import java.io.*;
import java.util.*;

// 210214

public class Main_BJ_2206_벽부수고이동하기 {
	static int N, M;
	static int[][] map;
	static int[][][] dist;
	static Queue<Integer[]> q;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static void bfs() {
		q.offer(new Integer[] {1, 1, 0, 1});
		dist[1][1][0] = 1;
		
		while(!q.isEmpty()) {
			Integer[] v = q.poll();
			int r = v[0], c = v[1], b = v[2]; // v[3]은 거리
			for(int d=0; d<4; d++) {

				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr>=1 && nr<=N && nc>=1 && nc<=M) {
					
					// 벽x 이동
					if(map[nr][nc]==0) {
						if(dist[nr][nc][b]==0) {
							q.offer(new Integer[] {nr, nc, b, v[3]+1});
							dist[nr][nc][b] = v[3]+1;
						}
					} else {
						if(b==0 && dist[nr][nc][1]==0) {
							// 벽o => 아직 벽 부순 적 없으면 이동
							q.offer(new Integer[] {nr, nc, 1, v[3]+1});
							dist[nr][nc][1] = v[3]+1;
						}
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
		map = new int[N+1][M+1];
		dist = new int[N+1][M+1][2];
		q = new ArrayDeque<Integer[]>();

		for(int r=1; r<=N; r++) {
			String[] str = br.readLine().split("");
			for(int c=1; c<=M; c++) {
				map[r][c] = Integer.parseInt(str[c-1]);
			}
		}
	
		bfs();
		
		if(dist[N][M][0]==0 && dist[N][M][1]==0) {
			System.out.println(-1);
		} else if(dist[N][M][0]==0 && dist[N][M][1]!=0) {
			System.out.println(dist[N][M][1]);
		} else if(dist[N][M][0]!=0 && dist[N][M][1]==0) {
			System.out.println(dist[N][M][0]);
		} else {
			System.out.println(Math.min(dist[N][M][0], dist[N][M][1]));
		}
		
		br.close();
	} // 
}
