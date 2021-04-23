import java.io.*;
import java.util.*;
// 210423

public class Main_BJ_17142_연구소3 {
	static int N, M, blank, virusCnt, min;
	static int[][] map;
	static ArrayList<int[]> virus;
	static boolean[] res; // 조합 결과
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Pos{
		int r, c, time;

		public Pos(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_17142_연구소3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		blank = 0;
		min = Integer.MAX_VALUE;
		map = new int[N][N];
		virus = new ArrayList<int[]>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = stoi(st.nextToken());
				if(map[i][j]==0) ++blank;
				if(map[i][j]==2) {
					++virusCnt;
					virus.add(new int[] {i, j});
				}
			}
		}
		res = new boolean[virusCnt];
		
		solve();
		br.close();
	}
	
	static void solve() {
		// 1. 활성화 시킬 M개의 바이러스 택
		combination(0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	// 1. 
	static void combination(int cnt, int L) {
		if(cnt==M) {
			// 2. bfs로 전파
			bfs();
			return;
		}
		if(L==virusCnt) return;
			
		res[L] = true;
		combination(cnt+1, L+1);
		
		res[L] = false;
		combination(cnt, L+1);
	}
	
	// 2
	static void bfs() {
		int[][] newMap = new int[N][N];
		int[][] v = new int[N][N];
		Queue<Pos> q = new ArrayDeque<Pos>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				newMap[i][j] = map[i][j];
			}
			Arrays.fill(v[i], -1);
		}
		
		for(int i=0; i<virusCnt; i++) {
			int[] virusPos = virus.get(i);
			
			if(res[i]) { // 활성화
				v[virusPos[0]][virusPos[1]] = 0;
				q.offer(new Pos(virusPos[0], virusPos[1], 0));
				
			} else { // 비활성화
				newMap[virusPos[0]][virusPos[1]] = 3;
			}
		}
		
	    int spread = 0;
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N || v[nr][nc]!=-1) continue;
				if(newMap[nr][nc]==0) {
					++spread;
					v[nr][nc] = cur.time+1;
					q.offer(new Pos(nr, nc, cur.time+1));
					
				} else if(newMap[nr][nc]==3) {
					v[nr][nc] = 0;
					q.offer(new Pos(nr, nc, cur.time+1));
				}
			}
		}

		if(spread==blank) {
			int time = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(v[i][j]>time) time = v[i][j];
				}
			}
			
			min = Math.min(min, time);
		}
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
