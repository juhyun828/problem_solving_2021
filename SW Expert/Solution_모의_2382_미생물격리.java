import java.io.*;
import java.util.*;
// 210424

public class Solution_모의_2382_미생물격리 {
	static StringBuilder sb;
	static int N, M, K;
	static ArrayList<Data>[][] map;
	static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
	static int[] dc = {0, 0, -1, 1};
	
	static class Data implements Comparable<Data>{
		int cnt, dir;

		public Data(int cnt, int dir) {
			super();
			this.cnt = cnt;
			this.dir = dir;
		}
		
		@Override
		public int compareTo(Data o) {
			// 수가 많은 순으로 (내림차순)
			return Integer.compare(o.cnt, this.cnt);
		}
		
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_모의_2382_미생물격리.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		
		int T = stoi(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			K = stoi(st.nextToken());
			
			map = new ArrayList[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = new ArrayList<Data>();
				}
			}
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int r = stoi(st.nextToken());
				int c = stoi(st.nextToken());
				int cnt = stoi(st.nextToken());
				int dir = stoi(st.nextToken())-1;
				map[r][c].add(new Data(cnt, dir));
			}
			
			solve(tc);
		}//
		 
		System.out.println(sb.toString());
		br.close();
	}

	static void solve(int tc) {
		for(int i=0; i<M; i++) {
			// 1. 미생물 이동
			map = move();
			// 2. 두 군집 이상은 분리
			divide();
		}
		
		int left = count();
		sb.append("#" + tc + " " + left + "\n");
	}

	static ArrayList<Data>[][] move() {
		ArrayList<Data>[][] newMap = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				newMap[i][j] = new ArrayList<Data>();
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j].size()==0) continue;
				
				Data d = map[i][j].get(0);
				int cnt = d.cnt;
				int dir = d.dir;
				
				int nr = i + dr[dir];
				int nc = j + dc[dir];
				
				// 가장자리
				if(nr<=0 || nr>=N-1 || nc<=0 || nc>=N-1) {
					cnt = cnt/2;
					dir = filpDir(dir);
					
					if(cnt<=0) continue;
					newMap[nr][nc].add(new Data(cnt, dir));
				} else {
					newMap[nr][nc].add(new Data(cnt, dir));
				}
				
			}
		}
		
		return newMap;
	}
	
	static void divide() {
		for (int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j].size()<=1) continue;
				
				// 2개 이상이면 가장 많은 군집의 방향
				Collections.sort(map[i][j]);
				int dir = map[i][j].get(0).dir;
				int newCnt = 0;
				
				for(int z=0; z<map[i][j].size(); z++) {
					newCnt += map[i][j].get(z).cnt;
				}
				map[i][j] = new ArrayList<Data>();
				map[i][j].add(new Data(newCnt, dir));

			}
		}

	}
	
	static int count() {
		int left = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j].size()<=0) continue;
				
				for(int z=0; z<map[i][j].size(); z++) {
					left += map[i][j].get(z).cnt;
				}
			}
		}
		
		return left;
	}

	static int filpDir(int dir) {
		if(dir==0) return 1;
		else if(dir==1) return 0;
		else if(dir==2) return 3;
		else if(dir==3) return 2;
		
		return 0;
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
}
