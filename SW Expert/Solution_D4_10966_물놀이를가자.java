import java.io.*;
import java.util.*;
// 210315

public class Solution_D4_10966_물놀이를가자 {
	static int N, M;
	static char[][] map;
	static int[][]visited;
	static int[] dr = {-1, 0, 1, 0}; 
	static int[] dc = {0, -1, 0, 1};
	static Queue<Position> q;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static class Position {
		int r;
		int c;
		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Position [r=" + r + ", c=" + c + "]";
		}
	}
	
	static void bfs() {
		while (!q.isEmpty()) {
			Position p = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || visited[nr][nc]>0 || map[nr][nc]=='W') continue;
				visited[nr][nc] = visited[p.r][p.c] + 1;
				q.offer(new Position(nr, nc));
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_D4_10966_물놀이를가자.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			
			map = new char[N][M];
			visited = new int[N][M];
			q = new ArrayDeque<>();
			
			for(int i=0; i<N; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j=0; j<M; j++) {
					if(map[i][j]=='W') {
						q.offer(new Position(i, j));
					}
				}
			}
			bfs();
			int ans = 0;
			sb.append("visited 배열 \n");
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					ans +=  visited[i][j];
					sb.append(visited[i][j] + " ");
				}
				sb.append("\n");
			}
			sb.append("#" + tc + " " + ans + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	} //
}
