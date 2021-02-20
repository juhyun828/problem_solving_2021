import java.io.*;
import java.util.*;
// 210220

public class Main_BJ_3109_빵집 {
	static int R, C;
	static char[][] map;
	static int cnt=0;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_BJ_3109_빵집.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int r=0; r<R;r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		makePipe();
		System.out.println(cnt);
		
		//printMap();
	} // main
	
	static void makePipe() {
		for(int r=0; r<R; r++) {
			visited[r][0] = true;
			dfs(r, 0); // 1열부터 시작			
		}		
	}
 	
	static int[] dr = {-1, 0, 1};
	static boolean dfs(int r, int c) {
		if (c==C-1) {
			++cnt;
			return true; // 파이프 놓기 성공
		}
		int nc = c+1;
		for(int d=0; d<3; d++) {
			int nr = r + dr[d];
			if(nr<0 || nr>=R || nc>=C || map[nr][nc]=='x' || visited[nr][nc]) continue;
			visited[nr][nc] = true;
			if(dfs(nr, nc)) return true;
		}
		return false; // 세 방향을 모두 갔는데 못감
	}
	
	static void printMap() {
		for(int r=0; r<R; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
