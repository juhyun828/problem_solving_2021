import java.io.*;
import java.util.*;
// 210317

public class Main_BJ_2206_벽부수고이동하기 {
	static int N, M;
	static int[][] map;
	static int[][][] visited;
	static Queue<Pos> q;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static class Pos {
		int r, c, flag; // flag 0 -> 벽 안부숨, 1 -> 벽 부숨

		public Pos(int r, int c, int flag) {
			this.r = r;
			this.c = c;
			this.flag = flag;
		}
	}
	
	static void bfs() {
		q.offer(new Pos(1, 1, 0)); // 시작점
		visited[1][1][0] = 1; // 시작점 거리 1
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(nr<1 || nr>N || nc<1 || nc>M || visited[nr][nc][p.flag]>0) continue;
				
				// 벽x -> 방문
				if(map[nr][nc]==0 ) {
					visited[nr][nc][p.flag] = visited[p.r][p.c][p.flag] + 1;
					q.offer(new Pos(nr, nc, p.flag));
				}
				else { // 벽인데
					// 아직 벽을 부순 적으면 벽 부수고 이동
					if(p.flag==0) {
						visited[nr][nc][1] = visited[p.r][p.c][p.flag] + 1;
						q.offer(new Pos(nr, nc, 1));
					}
				}

			}

		}

	}
	
	static void check() {
		if(visited[N][M][0]==0 && visited[N][M][1]==0) {
			// 둘 다 방문 x
			System.out.println(-1);
		} else if(visited[N][M][0]==0) {
			System.out.println(visited[N][M][1]);
		} else if (visited[N][M][1]==0) {
			System.out.println(visited[N][M][0]);
		} else {
			System.out.println(Math.min(visited[N][M][0], visited[N][M][1]));
		}
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_BJ_2206_벽부수고이동하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		 
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		map = new int[N+1][M+1];
		visited = new int[N+1][M+1][2]; // 세번째 1 - 벽 부셨을 때, 0 - 안부셨을 때
		q = new ArrayDeque<>();
		
		String[] str;
		for(int i=1; i<=N; i++) {
			str = br.readLine().trim().split("");
			for(int j=1; j<=M; j++) {
				map[i][j] = stoi(str[j-1]);
			}
		}
		
		bfs();
		
		check();
		
		br.close();
	}
}
