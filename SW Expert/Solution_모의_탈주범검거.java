import java.io.*;
import java.util.*;
// 210415

public class Solution_모의_탈주범검거 {
	static int N, M, R, C, L;
	static int[][] map, v;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] types;
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_모의_탈주범검거.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		types = new int[8][];
		types[1] = new int[] {0, 1, 2, 3};
		types[2] = new int[] {0, 1};
		types[3] = new int[] {2, 3};
		types[4] = new int[] {0, 3};
		types[5] = new int[] {1, 3};
		types[6] = new int[] {1, 2};
		types[7] = new int[] {0, 2};
		
		int T = stoi(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			R = stoi(st.nextToken()); // 맨홀 위치
			C = stoi(st.nextToken());
			L = stoi(st.nextToken()); // 탈출 소요 시간
			
			map = new int[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for(int j=0; j<M; j++) {
					map[i][j] = stoi(st.nextToken());
				}
 			}
			
			bfs();
			sb.append("#" + tc + " " + count() + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void bfs() {
		Queue<Pos> q = new ArrayDeque<Pos>(); 
		v = new int[N][M];
		
		// 시작점
		q.offer(new Pos(R, C));
		v[R][C] = 1;
		
		Pos cur;
		int nr, nc, type;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			type = map[cur.r][cur.c];
			
			for(int dir: types[type]) {
				nr = cur.r + dr[dir];
				nc = cur.c + dc[dir];

				if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]==0 || v[nr][nc]>0) continue;
				// 같은 방향을 바라보고 있는지 확인

				if(possible(dir, map[nr][nc])) {					
					q.offer(new Pos(nr, nc));
					v[nr][nc] = v[cur.r][cur.c] + 1;
				}			
			}
		}
		
	}
	
	static boolean possible(int dir, int nextType) {
		
		for(int nextDir: types[nextType]) {
			if(dir==0) { // 현재 상 -> 다음 하
				if(nextDir==1) return true;
				
			} else if(dir==1) { // 현재 하 -> 다음 상
				if(nextDir==0) return true;
				
			} else if(dir==2) { // 현재 좌 -> 다음 우
				if(nextDir==3) return true;
				
			} else if(dir==3) { // 현재 우 -> 다음 좌
				if(nextDir==2) return true;
			}
		}
		
		return false;
	}

	static int count() {
		int total=0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(v[i][j] > 0 && v[i][j] <= L)
					++total;
			}
		}
		
		return total;
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
