import java.io.*;
import java.util.*;
// 210421

public class Solution_D4_1868_파핑파핑지뢰찾기 {
	static int N, click;
	static char[][] map; // * -> 지뢰, . -> 빈칸
	static boolean[][] v;
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	static class Pos {
		int r,c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}			
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_D4_1868_파핑파핑지뢰찾기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = stoi(br.readLine());
			map = new char[N][N];
			v = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			// 빈칸들에서 시작
			click = 0;
			solve();
			
			sb.append("#" + tc + " " + click + "\n");
		} // tc

		System.out.println(sb.toString());
		br.close();
	}
	
	static void solve() {

		
		// 1. 빈 칸 중, 주위 8방의 지뢰 개수가 0이 될 칸들 먼저 세준다.
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]!='.') continue;
				if(countMine(i, j)==0) {
					++click;
					bfs(i, j);
				}
			}
		}
		
		// 2. 세어지지 못한 칸들을 세준다.
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]=='.') {
					++click;
					map[i][j] = (char) (countMine(i, j) + '0');
				}
			}
		}
	}
	
	static void bfs(int r, int c) {
		v[r][c] = true;
		Queue<Pos> q = new ArrayDeque<Pos>();
		q.offer(new Pos(r, c));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			int mine = countMine(cur.r, cur.c);
			map[cur.r][cur.c] = (char) (mine + '0');
			
			if(mine==0) {
				// 인접한 8방향 칸들 중, 아직 개수가 세어지지 않은 칸은 탐색 대상
				for(int d=0; d<8; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					if(nr<0 || nr>=N || nc<0 || nc>=N || v[nr][nc]) continue;
					if(map[nr][nc]=='.') {						
						v[nr][nc] = true;
						q.offer(new Pos(nr, nc));
					}
				}
			}
		}
	}
	
	static int countMine(int r, int c) {
		int mine = 0;
		
		for(int d=0; d<8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
			if(map[nr][nc]=='*') ++mine;
		}
		return mine;
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}