import java.io.*;
import java.util.*;

// 210214

public class Main_BJ_7569_토마토 {
	static int M, N, H; // M은 가로, N은 세로
	static int[][][] map;
	static int[][][] dist;
	static Queue<Integer[]> q;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static void bfs() {
		while(!q.isEmpty()) {
			Integer[] v = q.poll(); // h, r, c 순
			int h = v[0], r = v[1], c = v[2];
			
			// h 먼저
			int nh=0;
			for(int hh=0; hh<2; hh++) {
				if (hh==0) nh = h-1;
				else nh = h + 1;
				if (nh>=0 && nh<H && map[nh][r][c]==0 && dist[nh][r][c]==0) {
					map[nh][r][c]=1;
					dist[nh][r][c]= dist[h][r][c] + 1;
					q.offer(new Integer[] {nh, r, c});
				}
			}
			// 상하좌우
			for(int d=0; d<4; d++) {
				int nr = r + dr[d], nc = c + dc[d];
				if (nr>=0 && nr<N && nc>=0 && nc<M && 
						map[h][nr][nc]==0 && dist[h][nr][nc]==0) {
					map[h][nr][nc]=1;
					dist[h][nr][nc]= dist[h][r][c] + 1;
					q.offer(new Integer[] {h, nr, nc});
				}
			}
		}
	}
	
	static int check() {
		int max = 0;
		for(int h=0; h<H; h++) {
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if (map[h][r][c]==0) return -1;
					else max = Math.max(max, dist[h][r][c]);
				}
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M];
		dist = new int[H][N][M];
		q = new ArrayDeque<Integer[]>();
		

		for(int h=0; h<H; h++) {
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c=0; c<M; c++) {
					map[h][r][c] = Integer.parseInt(st.nextToken());
					if (map[h][r][c]==1) q.offer(new Integer[] {h, r, c});
				}
			}
		}
		
		bfs();
		System.out.println(check());
		br.close();
	}
}
