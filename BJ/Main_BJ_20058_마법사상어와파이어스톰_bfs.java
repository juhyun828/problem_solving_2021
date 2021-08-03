import java.io.*;
import java.util.*;
// 210420

public class Main_BJ_20058_마법사상어와파이어스톰_bfs {
	static int N, Q, NN, cnt;
	static int[][] map, newMap;
	static int[] L;
	static int[][] v;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_20058_마법사상어와파이어스톰.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		Q = stoi(st.nextToken());
		NN = (int) Math.pow(2, N);
		
		map = new int[NN][NN];
		L = new int[Q];
		
		for(int i=0; i<NN; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<NN; j++) {	
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<Q; i++) {
			L[i] = stoi(st.nextToken());
		}
			
		solve();
		br.close();
	}
	
	static void solve() {
		for(int i=0; i<Q; i++) {
			divide(L[i]);
			map = melt();
		}
		
		// 남은 얼음 양과 가장 큰 덩어리 크기 구하기
		int left=0;
		int max = 0;
		int idx=0;
		v = new int[NN][NN];
		for(int i=0; i<NN; i++) {
			for(int j=0; j<NN; j++) {
				left += map[i][j];
				if(map[i][j]!=0 && v[i][j]==0) {
					cnt = 1;
					bfs(i, j, ++idx);
					max = Math.max(max, cnt);
				}
			}
		}
		System.out.println(left);
		System.out.println(max);


	}
	
	static void divide(int level) {
		int size = (int) Math.pow(2, level);
		
		for(int i=0; i<NN; i+=size) {
			for(int j=0; j<NN; j+=size) {
				int sr = i, sc = j;
				int er = i+size-1, ec = j+size-1;
				
				roate(sr, sc, er, ec);
			}
		}
	}
	
	static void roate(int sr, int sc, int er, int ec) {
		// 열 먼저 읽는다.
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		for(int i=sc; i<=ec; i++) {
			for(int j=er; j>=sr; j--) {
				q.offer(map[j][i]);
			}
		}
		
		for(int i=sr; i<=er; i++) {
			for(int j=sc; j<=ec; j++) {
				map[i][j] = q.poll();
			}
		}
	}
	
	static int[][] melt() {
		newMap = new int[NN][NN];
		
		for(int i=0; i<NN; i++) {
			for(int j=0; j<NN; j++) {
				if(map[i][j]==0) continue;
				int ice=0;
				for(int d=0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(nr<0 || nr>=NN || nc<0 || nc>=NN) continue;
					if(map[nr][nc]>0) ++ice;
				}
				if(ice<3) newMap[i][j]= map[i][j]-1;
				else newMap[i][j]= map[i][j];
			}
		}
		return newMap;
	}
	

	// 남은 얼음 양과 가장 큰 덩어리 크기 구하기
	static void bfs(int r, int c, int idx) {
		v[r][c] = idx;
		Queue<Pos> q = new ArrayDeque<Pos>();
		q.offer(new Pos(r, c));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr<0 || nr>=NN || nc<0 || nc>=NN || v[nr][nc]!=0 || map[nr][nc]==0) continue;
				v[nr][nc] = idx;
				q.offer(new Pos(nr, nc));
				++cnt;
			}
		}
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}