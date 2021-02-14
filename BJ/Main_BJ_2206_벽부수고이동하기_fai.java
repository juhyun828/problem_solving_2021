import java.io.*;
import java.util.*;

// 210214

public class Main_BJ_2206_벽부수고이동하기_fail {
	static int N, M;
	static int[][] map;
	static int[][] dist;
	static int min = Integer.MAX_VALUE;
	static Queue<Integer[]> q;
	static Queue<Integer[]> zero;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static int bfs() {
		q.offer(new Integer[] {1, 1});
		dist[1][1] = 1;
		
		while(!q.isEmpty()) {
			Integer[] v = q.poll();
			int r = v[0];
			int c = v[1];
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr>=1 && nr<=N && nc>=1 && nc<=M &&
						map[nr][nc]==0 && dist[nr][nc]==0) {
					dist[nr][nc] = dist[r][c] + 1;
					q.offer(new Integer[] {nr, nc});
					if(nr==N && nc==M) return dist[N][M];
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		
		zero = new ArrayDeque<Integer[]>();
		
		for(int r=1; r<=N; r++) {
			String[] str = br.readLine().split("");
			for(int c=1; c<=M; c++) {
				map[r][c] = Integer.parseInt(str[c-1]);
				if(map[r][c]==1) zero.offer(new Integer[] {r,c});
			}
		}
	
		boolean flag = false;
		while(!zero.isEmpty()) {
			Integer[] b = zero.poll(); 
			map[b[0]][b[1]] = 0; // 벽 하나씩 부숨
			
			q = new ArrayDeque<Integer[]>();
			dist = new int[N+1][M+1];
			int tmp = bfs();
			if (tmp!=-1) {
				flag = true;
				min = Math.min(min, tmp);
			}
			map[b[0]][b[1]] = 1;
		} // while
		
		if(!flag) min=-1;
		System.out.println(min);
		br.close();
	} // 
}
