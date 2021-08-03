import java.io.*;
import java.util.*;
// 210311

public class Solution_모의_1949_등산로조성 {
	static int N, K, maxS, maxHeight;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static void dfs(int r, int c, int s, boolean flag) {
		visited[r][c]=true;
		maxS = Math.max(maxS, s);
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr>=1 && nr<=N && nc>=1 && nc<=N && !visited[nr][nc]) {
				if(map[r][c] > map[nr][nc]) {
					dfs(nr, nc, s+1, flag);
				} else if((map[nr][nc]-map[r][c])<K && !flag) {
					int before = map[nr][nc];
					map[nr][nc] = map[r][c]-1; 
					// map[nr][nc] -1으로 깎으면 안됨! 
					// 현재 위치의 값보다 1만 작아도 이동 가능
					dfs(nr, nc, s+1, !flag);
					map[nr][nc] = before; // 값 복구
				}
					
			}
		}
		visited[r][c]=false;
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_모의_1949_등산로조성.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			K = stoi(st.nextToken());
			maxS=0; maxHeight=0;
			
			map = new int[N+1][N+1];
			visited = new boolean[N+1][N+1];
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					map[i][j] = stoi(st.nextToken());
					maxHeight = Math.max(maxHeight, map[i][j]);
				}
			}
			
			// 탐색
			for(int ri=1; ri<=N; ri++) {
				for(int ci=1; ci<=N; ci++) {
					if(map[ri][ci]==maxHeight && !visited[ri][ci]) {
						dfs(ri, ci, 1, false);
					}
				}
			}
	
			sb.append("#" + tc + " " + maxS + "\n");
			
		} // tc
		System.out.println(sb.toString());
		
		br.close();
	}//
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
