import java.io.*;
import java.util.*;
// 210326

public class Main_BJ_14502_연구소 {
	static int N, M, max;
	static int[][] map, mapClone;
	
	static class Virus {
		int r, c;

		public Virus(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void makeWall(int cnt) { // dfs로 벽 세우기
		if(cnt==3) {
			spreadVirus();
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					map[i][j] = 1;
					makeWall(cnt+1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static void spreadVirus() { // bfs로 퍼뜨리기
		mapCopy();
		
		Queue<Virus> q = new ArrayDeque<Virus>();
		boolean[][] v = new boolean[N][M];
		
		// 1. q에 바이러스 위치 넣기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(mapClone[i][j]==2) {
					q.offer(new Virus(i, j));
					v[i][j] = true;
				}
			}
		} 
		
		// 2. 퍼뜨리기
		Virus cur = null; 
		int nr, nc;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int d=0; d<4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || v[nr][nc]) continue;
				if(mapClone[nr][nc]==0) {
					mapClone[nr][nc] = 2;
					q.offer(new Virus(nr, nc));
					v[nr][nc] = true;
				}
			}
		}
		
		// 3. 남은 safeZone 세기
		int safeZone = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(mapClone[i][j]==0) ++safeZone;
			}
		}
		
		max = Math.max(max, safeZone);
		
	}
	
	static void mapCopy() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				mapClone[i][j] = map[i][j];
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_BJ_14502_연구소.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		max = 0;
		map = new int[N][M];
		mapClone = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = stoi(st.nextToken());
			}			
		}
		
		makeWall(0);

		System.out.println(max);
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
