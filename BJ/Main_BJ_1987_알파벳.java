import java.io.*;
import java.util.*;

// 210218

public class Main_BJ_1987_알파벳 {
	static int R, C;
	static char[][] map;
	static HashMap<Character, Boolean> visitedDict;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int max = 0;

	static void dfs(int r, int c, int dist) {
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr>=0&&nr<R&&nc>=0&&nc<C) {
				char next = map[nr][nc];
				if(!visitedDict.get(next)) {
					// 방문 x
					visitedDict.put(next, true);
					dfs(nr, nc, dist+1);
					visitedDict.put(next, false);
				}
			}
		}
		max = Math.max(max, dist);
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_BJ_1987_알파벳.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		map = new char[R][C];
		visitedDict = new HashMap<>();

		for(int r=0; r<R; r++) {
			map[r] = br.readLine().trim().toCharArray();
			for(int c=0; c<C; c++) {
				visitedDict.put(map[r][c], false);
			}
		}
		visitedDict.put(map[0][0], true); // 말의 초기 위치는 무조건 방문
	
		dfs(0, 0, 1);
		System.out.println(max);
		
		br.close();
	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
