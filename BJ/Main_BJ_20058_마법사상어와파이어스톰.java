import java.io.*;
import java.util.*;
// 210416

public class Main_BJ_20058_마법사상어와파이어스톰 {
	static int N, Q, NN;
	static int nn; // 작은 한 변의 길이
	static int[][] map, newMap;
	static int[] LL;
	static int total, cnt, max;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_20058_마법사상어와파이어스톰.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		Q = stoi(st.nextToken()); // 단계 수
		NN = (int)Math.pow(2, N); // 전체 격자 크기
		map = new int[NN+1][NN+1];
		total = 0; 
		cnt=0; max=0;
		
		LL = new int[Q];
		for(int i=1; i<=NN; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1;j<=NN; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
	
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<Q; i++)
			LL[i] = stoi(st.nextToken());
		
		solve();
		System.out.println(total);
		System.out.println(max);
		
		br.close();
	}
	
	static void solve() {
		
		for(int l=0; l<Q; l++) {
			// 1&2. 작은 사각형별로 나눈 후 돌린다.
			map = divide(LL[l]);
			// 3. 회전이 끝나면 얼음과 3명 이상 맞닿지 않은 구역은 얼음이 녹는다.
			map = melt();
		}
		// 4. 남은 얼음의 총 양과 가장 큰 덩어리 수를 찾는다.
		count();
	}
	
	static boolean[][] v;
	static void count() {
		// 4. 남은 얼음의 총 양과 가장 큰 덩어리 수를 찾는다.
		// - cf) 가장 큰 덩어리 찾기 - 2667: 단지번호 붙이기
		
		v = new boolean[NN+1][NN+1];
		for(int i=1; i<=NN; i++) {
			for(int j=1; j<=NN; j++) {
				total += map[i][j];
				if(map[i][j]==0) {
					v[i][j] = true;
				} else {
					if(!v[i][j]) {
						cnt = 1;
						dfs(i, j);
						max = Math.max(max, cnt);
					}
				}
			}
		}
	}
	
	static void dfs(int r, int c) {
		// 4-1. 가장 큰 덩어리 찾기
		v[r][c] = true;
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr<1||nr>NN||nc<1||nc>NN 
					||v[nr][nc]||map[nr][nc]==0) continue;
			v[nr][nc]=true;
			++cnt;
			dfs(nr, nc);
		}
	}
	
	// 1. 작은 사각형들로 나누기
	static int[][] divide(int L) {
		newMap = new int[NN+1][NN+1];	
		nn = (int) Math.pow(2, L);
		int sr, sc, er, ec;
		for(int i=1; i<=NN; i+=nn) {
			for(int j=1; j<=NN; j+=nn) {
				sr = i; sc = j;
				er = sr+(nn-1); ec = sc+(nn-1);
				// 2. 시계 방향으로 90도 돌리기
				rotate(sr, sc, er, ec);
			}
		}

		return newMap;
	}
	
	static void rotate(int sr, int sc, int er, int ec) {
		// 2. 시계 방향으로 90도 돌리기
		Queue<Integer> q = new ArrayDeque<Integer>();
	
		// 행 기준으로 읽지 않고, 열 기준으로 밑의 행부터 읽는다. 
		for (int j = sc; j <= ec; j++) {
			for (int i = er; i >= sr; i--) {
				q.offer(map[i][j]);
			}
		}
		
		loop:
		for(int i=sr; i<=er; i++) {
			for(int j=sc; j<=ec; j++) {
				if(q.isEmpty()) break loop;
				newMap[i][j] = q.poll(); 
			}
		}
	}
	
	static int[][] melt() {
		// 3. 얼음과 3명 이상 맞닿지 않은 구역은 얼음이 녹는다.
		int[][] tmpMap = new int[NN+1][NN+1];
		
		for(int i=1; i<=NN; i++) {
			for(int j=1; j<=NN; j++) {
				if(map[i][j]==0) continue;
				int cnt=0;
				for(int d=0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(nr<1||nr>NN||nc<1||nc>NN) continue;
					if(map[nr][nc]>0) ++cnt;
				}
				if(cnt<3) tmpMap[i][j]=map[i][j]-1;
				else tmpMap[i][j]=map[i][j];
			}
		}
		return tmpMap;
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
