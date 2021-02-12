import java.io.*;
import java.util.*;
// 210212

public class Main_BJ_2667_단지번호붙이기 {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int total = 0, cnt = 0;
	
	static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr>=0 && nr<N && nc>=0 && nc<N 
					&& map[nr][nc]==1 && !visited[nr][nc]) {
				++cnt;
				visited[nr][nc] = true;
				dfs(nr, nc);
			} 
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_2667_단지번호붙이기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		ArrayList<Integer> list = new ArrayList<Integer>();

		for(int r=0; r<N; r++) {
			String[] arr = br.readLine().split("");
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(arr[c]);
			}
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if (map[r][c] == 0) {
					visited[r][c] = true;
				}
				else {
					if (!visited[r][c]) {		
						cnt = 1;
						dfs(r, c);
						list.add(cnt);
						total++;
					}
				}
			}
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		sb.append(total + "\n");
		for(int i=0; i<total; i++) {
			sb.append(list.get(i) + "\n");
		}
		System.out.println(sb.toString());
		
		br.close();
	} // main
}
