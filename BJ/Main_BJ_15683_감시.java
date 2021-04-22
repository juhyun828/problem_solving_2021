import java.io.*;
import java.util.*;
// 210421

public class Main_BJ_15683_감시 {
	static int N, M, cctvCnt, min;
	static int[] res;
	static int[][] map; // 0: 빈칸, 1~5: CCTV, 6: 벽
	static int[] dirTotal = {0, 4, 2, 4, 4, 1};
	static int[] dirLen = {0, 1, 2, 2, 3, 4};
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][][] dirs = {
			{}, // dummy
			{ {1},{2},{3},{0} }, // 1
			{ {3, 1}, {0, 2}}, // 2
			{ {0,1}, {1,2}, {2,3}, {0,3} }, // 3
			{ {0,1,3}, {0,1,2}, {1,2,3}, {0,2,3} }, // 4
			{ {0,1,2,3}} // 5
	};
	
	static ArrayList<CCTV> list;
	static class CCTV {
		int r, c, type;
		public CCTV(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
		@Override
		public String toString() {
			return "CCTV [r=" + r + ", c=" + c + ", type=" + type + "]\n";
		}
	}

	public static void main(String[] args) throws Exception {
		
		//System.setIn(new FileInputStream("res/input_BJ_15683_감시.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][M];
		cctvCnt = 0;
		list = new ArrayList<CCTV>();
		min = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = stoi(st.nextToken());
				if(map[i][j]>=1 && map[i][j]<=5) {
					// CCTV
					++cctvCnt;
					list.add(new CCTV(i, j, map[i][j]));
				}
			}
		}
		
		if(cctvCnt==0) {
			int blank=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==0) ++blank;
				}
			}
			System.out.println(blank);
			 
		} else {
			res = new int[cctvCnt];
			dfs(0); // 0번째 CCTV부터 방향 결정
			System.out.println(min);
			
		}
	
		br.close();
	}
	
	static void dfs(int depth) {
		if(depth==list.size()) {
			look();
			return;
		}
		
		// i번째 CCTV의 방향 결정
		int type = list.get(depth).type;
		for(int i=0; i<dirTotal[type]; i++) {
			res[depth] = i;
			dfs(depth+1);
		}
		
	}
	
	static void look() {
		int[][] v = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==6)
					v[i][j] = 6;
			}
		}
		
		for(int i=0; i<list.size(); i++) {
			int dirType = res[i];
			CCTV cur = list.get(i);
			int cctvType = cur.type;
			
			v[cur.r][cur.c] = cur.type*10;
			int[] currentDirs = dirs[cctvType][dirType];
			for (int d = 0; d < currentDirs.length; d++) {
				int nd = currentDirs[d];
				
				for (int k = 1; k <= Math.max(N, M); k++) {
					int nr = cur.r + dr[nd] * k;
					int nc = cur.c + dc[nd] * k;

					// 범위를 벗어나거나 벽을 만나면 더 이상 감시 못함
					if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 6)
						break;
					if (v[nr][nc] == 0) {
						v[nr][nc] = cur.type;
					}
				}
			}
		}
		
		countBlind(v);
	}
	
	static void countBlind(int[][] v) {
		int cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0 && v[i][j]==0) ++cnt;
			}
		}
		min = Math.min(min, cnt);
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}