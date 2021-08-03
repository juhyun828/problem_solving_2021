import java.io.*;
import java.util.*;
// 210420

public class Main_BJ_16236_아기상어_2 {
	static int N, sharkR, sharkC, sharkSize, eatCnt, res; // 1초에 1칸 이동
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Fish implements Comparable<Fish>{
		int r, c, dist;

		public Fish(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Fish o) {
			if(this.dist == o.dist) {
				if(this.r == o.r) {
					return Integer.compare(this.c, o.c);
				}
				return Integer.compare(this.r, o.r);
			}
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_16236_아기상어.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
	
		N = stoi(br.readLine());
		map = new int[N][N];
		res = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = stoi(st.nextToken());
				if(map[i][j]==9) {
					sharkR = i;
					sharkC = j;
					sharkSize = 2;
					eatCnt = 0;
				}
			}
		}		
		
		solve();
		br.close();
	}
	
	static void solve() {
		
		while(true) {
			boolean help = findFish();
			if(help) break;			
		}
		
		System.out.println(res);
	}
	
	static boolean findFish( ) {
		// 현재 상어 위치에서 먹을 수 있는 물고기들과의 거리 계산
		ArrayList<Fish> fishList = new ArrayList<Fish>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==0 || map[i][j]==9 || map[i][j] >= sharkSize) continue;
				int dist = getDist(i, j);
				if(dist == Integer.MAX_VALUE) continue;
				fishList.add(new Fish(i, j, dist));
			}
		}
		
		if(fishList.size()==0) return true;

		// 정렬하여 가장 가까운 -> 위에 있는 -> 왼쪽에 있는 물고기 찾기
		Collections.sort(fishList);
		
		Fish f  = fishList.get(0);
		
		// 상어가 이동
		res += f.dist;
		map[sharkR][sharkC] = 0;
		sharkR = f.r;
		sharkC = f.c;
		
		// 상어가 먹는다
		map[sharkR][sharkC] = 9; // 0 하면 안됨!!
		
		// 상어 크기 증가
		++eatCnt;
		if(eatCnt == sharkSize) {
			++sharkSize;
			eatCnt = 0;
		}
		
		return false;
	}
	
	static int getDist(int r, int c) {
		// 현재 상어가, 자신보다 큰 물고기를 피해가며
		// 해당 물고기까지 도달하기까지의 거리
		
		int[][] v = new int[N][N];
		for(int i=0; i<N; i++)
			Arrays.fill(v[i], -1);
		Queue<Pos> q = new ArrayDeque<Pos>();
		
		// 상어 위치
		v[sharkR][sharkC] = 0; // 시작 거리는 0
		q.offer(new Pos(sharkR, sharkC));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			if(cur.r == r && cur.c == c) {
				return v[cur.r][cur.c];
			} 
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				// v[nr][nc]!=0 아님!!
				if(nr<0 || nr>=N || nc<0 || nc>=N || 
						map[nr][nc]>sharkSize || v[nr][nc]!=-1) continue;
				
				v[nr][nc] = v[cur.r][cur.c] + 1;
				q.offer(new Pos(nr, nc));
			}
			
		}
		return Integer.MAX_VALUE; // 도달할 수 없음
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}