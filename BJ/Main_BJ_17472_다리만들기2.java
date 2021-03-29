import java.io.*;
import java.util.*;
// 210329

public class Main_BJ_17472_다리만들기2 {
	static int N, M, islandNum;
	static int[][] map;
	static int min;
	static boolean[][] v;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int[][] adjMatrix;
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	// 1. 섬 번호 붙이기
	static void nameIsland(int r, int c) {
		Queue<Pos> q = new ArrayDeque<>();
		map[r][c] = islandNum;
		q.offer(new Pos(r, c));
		v[r][c] = true;
		
		Pos cur;
		int nr, nc;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int d=0; d<4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				if(nr<0 || nr>=N || nc<0 || nc>=M || v[nr][nc] || map[nr][nc]==0) continue;
				map[nr][nc] = islandNum;
				q.offer(new Pos(nr, nc));
				v[nr][nc] = true;
			}
			
		}
	}

	// 2. dfs로 건설할 수 있는 모든 다리 구하기
	static void go(int r, int c, int dir, int islandIdx, int len) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		if(nr<0 || nr>=N || nc<0 || nc>=M ) return;
		
		if(len==1 && map[nr][nc]!=0) {
			// 길이 1인 다리는 건설할 수 없다.
			return;
		}
		
		if(map[nr][nc]==0) {
			go(nr, nc, dir, islandIdx, len+1);
		}	
		
		if(map[nr][nc]!=0 && map[nr][nc]!=islandIdx) {
			// 다리 놓기 가능
			int from = islandIdx;
			int to = map[nr][nc];
			if(adjMatrix[from][to] > len) {
				adjMatrix[from][to] = len;
				adjMatrix[to][from] = len;
			}
			return;
		}
		return;
	}
	
	// 3. Prim
	public static int prim(int islandNum) {
		boolean[] pv = new boolean[islandNum+1]; // 정점 방문 여부 체크
		int[] minEdge = new int[islandNum+1]; // 신장트리에 연결된 최소 간선 비용
		
		int totalWeight = 0; // 최종 가중치의 합
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0; // 임의의 정점 1을 시작 정점으로 처리
		
		for(int c=1; c<=islandNum; c++) {
			int min = Integer.MAX_VALUE;
			int minVertext = 0;
			
			// 신장트리에 연결되지 않은 정점 중 minEdget 비용이 최소인 정점
			for(int i=1; i<=islandNum; i++) {
				if(!pv[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertext = i;
				}
			}// 
			
			totalWeight += min;
			pv[minVertext] = true;
			
			// 선택 정점에서부터의 최소 거리를 찾아 비용 update
			for(int i=1; i<=islandNum; i++) {
				if(!pv[i] && adjMatrix[minVertext][i]!=0 
						&& minEdge[i] > adjMatrix[minVertext][i]) {
					minEdge[i] = adjMatrix[minVertext][i];
				}
			}
		}

		for(int i=1; i<=islandNum; i++) {
			if(minEdge[i]==Integer.MAX_VALUE)
				return -1;
		}
		
		return totalWeight;
	}
	
	static void printMap() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
	
	/////////////////////////////////////////////////
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_17472_다리만들기2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = stoi(st.nextToken());
 			}
		}
	
		islandNum = 0;
		// 1. 섬에 이름 붙이기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1 && !v[i][j]) {
					++islandNum;
					nameIsland(i, j);
				}
			}
		}
		
//		printMap();
		
		// 2. 건설할 수 있는 모든 다리 건설
		// 최단 거리로 인접 행렬 만들기
		int nr, nc;
		adjMatrix = new int[islandNum+1][islandNum+1]; // 섬은 1부터 시작
		// 인접행렬 최대값으로 초기화
		for(int i=0; i<=islandNum; i++) {
			Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
		}
		
		for(int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(map[i][j]!=0) {
					for(int d=0; d<4; d++) {
						nr = i + dr[d];
						nc = j + dc[d];
						if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
						if(map[nr][nc]==0)
							go(nr, nc, d, map[i][j], 1);
					}
				}
			}
		}
		
		// 3. Prim 알고리즘
		int ans = prim(islandNum);
		System.out.println(ans);
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
