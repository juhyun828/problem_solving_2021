import java.io.*;
import java.util.*;
// 210419

public class Main_BJ_17142_연구소3 {
	static int N, M, minTime, size, blank;
	static int[][] map; // 0: 빈칸, 1: 벽, 2: 바이러스
	static ArrayList<Pos> virusList;
	static boolean[] v;

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
		map = new int[N][N];
		virusList = new ArrayList<Pos>();
		blank = 0;
		minTime = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = stoi(st.nextToken());
				if(map[i][j]==2) virusList.add(new Pos(i, j, 0));
				if(map[i][j]==0) ++blank;
			}
		}
		
		size = virusList.size();
		v = new boolean[size];
		
		// M개의 바이러스 뽑기
		if(blank==0) {
			minTime = 0;
		} else {
			comb(0, 0);
		}
		
		System.out.println(minTime==Integer.MAX_VALUE ? -1 : minTime);
		
		br.close();
	}
	
	// 1. 활성화 시킬 M개의 바이러스 택
	static void comb(int cnt, int L) {
		if(cnt==M) {
			spread();
			return;
		}
		
		if(L==size) return;
		
		v[L] = true;
		comb(cnt+1, L+1);
		v[L] = false;
		comb(cnt, L+1);
	}
	
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	// 2. 선택된 바이러스만 활성화시킨 후 파이러스를 퍼드린다.
	
	static void spread() {
		int[][] newMap = new int[N][N];
		boolean [][] vv = new boolean[N][N];
		
		Queue<Pos> q = new ArrayDeque<Pos>();
		// 선택된 바이러스만 활성화시킴
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				newMap[i][j] = map[i][j];
		}
		
		for(int i=0; i<v.length; i++) {
			if(v[i]) {
				// 선택된 바이러스는 큐에 넣는다,
				Pos p = virusList.get(i);
				vv[p.r][p.c] = true;
				q.offer(p);
			} else {
				// 선택되지 않은 바이러스는 지도에서 3으로 표기한다.
				Pos p = virusList.get(i);
				newMap[p.r][p.c] = 3; // 비활성화 바이러스
			}
		}

		int time = 0; // 시간
		int infectedCnt = 0; // 바이러스를 전염시킨 빈칸 개수
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				// 범위를 넘거나, 벽이거나, 이미 바이러스가 퍼진 곳이면x
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;	
				if(newMap[nr][nc]==1 || vv[nr][nc]) continue;
				
				if(newMap[nr][nc]==0) {
					++infectedCnt;
					time = cur.time+1;
					if(time>minTime) return;
					// 가지치기 한건데 시간이 더드네..
				}
				
				vv[nr][nc] = true;
				q.offer(new Pos(nr, nc, cur.time+1)); 
				// time을 집어넣으면 안된다. 빈칸이 아닌 경우도 있기에
				 
			} // for
		} // while
		
		if(blank == infectedCnt) {
			// 전염 다 시켰을 때만 minTime 갱신
			minTime = Math.min(minTime, time);
		} 

	} 
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}

