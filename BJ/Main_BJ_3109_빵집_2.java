import java.io.*;
import java.util.*;
// 210220

public class Main_BJ_3109_빵집_2  {
	static int R, C;
	static char[][] map;
	static int cnt=0;
	static boolean root;
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_BJ_3109_빵집.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		map = new char[R][C];
		
		for(int r=0; r<R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		makePipe();
		System.out.println(cnt);
	}
	
	static void makePipe() {
		for(int r=0; r<R; r++) {
			root = false; // root까지 가기
			dfs(r, 0);
		}
	}
	
	static int[] dr = {-1, 0, 1};
	static void dfs(int r, int c) {
		if (c==C-1) {
			root = true;
			++cnt;
			return;
		}
		
		int nc = c+1;
		for(int d=0; d<3; d++) {
			int nr = r + dr[d];
			if (nr < 0 || nr >= R || nc >= C || map[nr][nc] == 'x')
				continue;
			map[nr][nc] = 'x';
			dfs(nr, nc);
			if(root) return;

		}
	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
