import java.io.*;
import java.util.*;
// 210306

public class Main_BJ_3109_빵집 {
	static int R, C, cnt=0;
	static char[][] map;
	static boolean[][] visited;
	static char pipe = 'p';

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	// 디버깅용
	static void printMap() {
		for(int i=0; i<R; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("=========================");
	} // 
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_3109_빵집.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		map = new char[R][C]; // 0번 0행부터 시작 
		// 0열은 가스관, C-1열은 가게이다.
		visited = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		makePipe();
		System.out.println(cnt);

		br.close();
	} // main

	static void makePipe() {
		// 0행부터 파이프 놓기 시도
		for(int r=0; r<R; r++) {
			pipe = (char)(r + '0'); // int ->  char
			map[r][0] = pipe; // 디버깅용
			
			visited[r][0] = true; // 0행은 가스관
			dfs(r, 0); // r행의 0열부터 dfs 탐색	
			
			// 디버깅
			// 한 행부터 파이프 놓기를 시도한 결과를 출력한다.
//			System.out.println(r+"행에서 시도, cnt = " + cnt);
//			printMap();
		}
	} // 
	
	static int[] dr = {-1, 0, 1}; // 우상, 우, 우하 순
	static boolean dfs(int r, int c) {
		if(c==(C-1)) {
			++cnt;
			return true; 
			// 파이프 놓기 성공
		}
		
		int nc = c+1; // ->
		for(int d=0; d<3; d++) {
			int nr = r + dr[d];
			if(nr<0 || nr>=R || visited[nr][nc] || map[nr][nc]!='.') 
				continue; // 파이프를 놓을 수 없는 경우 다음 (d+1) 방향 탐색
			// 파이프를 놓을 수 있는 경우 
			map[nr][nc] = pipe; // 디버깅용
			if(dfs(nr, nc)) return true; 
			// 1. 파이프 놓기에 성공할 경우 true를 리턴하여 다음 (d+1) 방향을 탐색하지 않도록 가지치기
		}
		return false; // 2. 세방향 어디로도 가지 못한다면 가지치기. 
					  // (r, c)에서 더이상 파이프를 놓을 수없다면 r+N 행에서도 놓을 수 없다.
	}
}
