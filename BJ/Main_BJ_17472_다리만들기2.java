import java.util.*;
import java.io.*;
// 210424

public class Main_BJ_17472_다리만들기2 {
	static int N, M;
	static int[][] map;
	static StringBuilder sb;
	static int[][] adjMatrix;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_17472_다리만들기2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		solve();
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void solve() {
		// 1. 섬 번호 붙이기
		int islandNum = 0;
		v = new boolean[N][M]; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]!=0 && !v[i][j]) {
					bfs(i, j, ++islandNum);
				}
			}
		}

		adjMatrix = new int[islandNum+1][islandNum+1]; // 섬은 1부터 시작
		// 인접행렬 최대값으로 초기화
		for(int i=0; i<=islandNum; i++) {
			Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
		}
		
		// 2. 섬 건설 (dfs)
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]!=0) {
					for(int d=0; d<4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
						if(map[nr][nc]==0) {
							// 섬 건설 시도
							build(nr, nc, d, map[i][j], 1);
						}
						
					}
					
				}
			}
		}
		
		// 3. 거리 세기 (Prim 알고리즘)
		sb.append(prim(islandNum));
	}
	
	// 1.
	static void bfs(int r, int c, int islandNum) {
		map[r][c] = islandNum;
		v[r][c] = true;
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]==0 || v[nr][nc]) continue;
				v[nr][nc] = true;
				map[nr][nc] = islandNum;
				q.offer(new int[] {nr, nc});
			}
		}
	}
	
	// 2.
	static void build(int r, int c, int dir, int islandNum, int len) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		// 경계에 막혀있는 곳은 건설 불가
		if(nr<0 || nr>=N || nc<0 || nc>=M) return;
		
		// 길이 1인 곳은 건설 불가
		if(len==1 && map[nr][nc]!=0) return;
		
		if(map[nr][nc]==0) build(nr, nc, dir, islandNum, len+1);
		
		if(map[nr][nc]!=0 && map[nr][nc]!=islandNum) {
			// 건설 시도
			int from = islandNum;
			int to = map[nr][nc];
			
			if(adjMatrix[from][to] > len) {
				adjMatrix[from][to] = len;
				adjMatrix[to][from] = len;
			}
		}
	}
	
	// 3. 거리 세기
	static int prim(int islandNum) {
		boolean[] pv = new boolean[islandNum+1]; // 정점방문 여부 체크
		int[] minEdge = new int[islandNum+1]; // 신장트리에 연결된 최소 간선 비용
		int totalWeight = 0; // 최종 가중치의 합
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0; // 임의의 정점 1을 시작 정점으로 처리
		
		for(int c=1; c<=islandNum; c++) {
			int min = Integer.MAX_VALUE;
			int minVertext = 0;
			
			// 신장트리에 연결되지 않은 정점 중 minEdge 비용이 최소인 정점
			for(int i=1; i<=islandNum; i++) {
				if(!pv[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertext = i;
				}
			}
			
			totalWeight += min;
			pv[minVertext] = true;
			
			// 선택 정점에서부터의 최소 거리를 찾아 비용 update
			for(int i=1; i<=islandNum; i++) {
				if(!pv[i] && adjMatrix[minVertext][i]!=0 
						&& minEdge[i] > adjMatrix[minVertext][i]) {
					minEdge[i] = adjMatrix[minVertext][i];
				}
			}
			
		} // for
		
		for(int i=1; i<=islandNum; i++) {
			if(minEdge[i]==Integer.MAX_VALUE) 
				return -1; // 갈 수 없는 경우
		}
		
		return totalWeight;
	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
