import java.io.*;
import java.util.*;
// 210423

public class Main_BJ_20056_마법사상어와파이어볼 {
	static int N, M, K;
	static ArrayList<int[]>[][] map; // m, s, d 순서
	static int[] dr = {-1, -1, 0, 1, 1, 1 , 0, -1};
	static int[] dc = { 0, 1,  1, 1, 0, -1, -1, -1}; // // 0 1 2 3 4 5 6 7
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_20056_마법사상어와파이어볼.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		map = new ArrayList[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] = new ArrayList<int[]>();
			}
		}
		
		// 파이어볼 정보 입력
		int r, c, m, s, d;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			r = stoi(st.nextToken());
			c = stoi(st.nextToken());
			m = stoi(st.nextToken());
			s = stoi(st.nextToken());
			d = stoi(st.nextToken());
			map[r][c].add(new int[] {m, s, d});
		}
		
		solve();

		br.close();
	}
	
	static void solve() {
		for(int i=0; i<K; i++) {
			map = move();
			divide();
		}
		System.out.println(sumLeftM());
	}
	
	// 1.
	static ArrayList<int[]>[][] move() {
		ArrayList<int[]>[][] newMap = new ArrayList[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				newMap[i][j] = new ArrayList<int[]>();
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j].size()==0) continue;
				
				for(int z=0; z<map[i][j].size(); z++) {
					int[] firestorm = map[i][j].get(z);
					int m = firestorm[0];
					int s = firestorm[1];
					int d = firestorm[2];
					
					int nr = i + dr[d]*s;
					int nc = j + dc[d]*s;
					
					while(nr<1 || nr>N || nc<1 || nc>N) {
						if(nr<1) nr += N;
						else if(nr>N) nr -=N;
						if(nc<1) nc += N;
						else if(nc>N) nc -=N;
					}
					
					newMap[nr][nc].add(new int[] {m, s, d});
				}
			}
		}

		return newMap;
	}
	
	// 2.
	static void divide() {
		// 여러 파이어볼이 들어있는 칸은 합친 후 4개로 쪼갠다.
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j].size()<=1) continue;
				
				int mSum = 0, sSum = 0, dModSum = 0;	
				// 2로 나눈 값 나머지의 합이 0이거나 사이즈랑 같으면 모두 짝수/홀수
				// 아니면 섞인것
				for(int z=0; z<map[i][j].size(); z++) {
					mSum += map[i][j].get(z)[0];
					sSum += map[i][j].get(z)[1];
					dModSum += map[i][j].get(z)[2]%2;
				}
				
				int nm = mSum/5;
				int ns = sSum/map[i][j].size();
				boolean even = false;
				if(dModSum==0 || dModSum==map[i][j].size()) even = true;
				
				map[i][j] = new ArrayList<int[]>();
				if(nm<=0) continue; // 소멸되어 사라짐
				else {
					if(even) { // 짝수 방향들
						for(int nd=0; nd<=6; nd+=2) {
							map[i][j].add(new int[] {nm, ns, nd});
						}
						
					} else { // 홀수 방향들
						for(int nd=1; nd<=7; nd+=2) {
							map[i][j].add(new int[] {nm, ns, nd});
						}
					}
				}
			}
		}
	}

	// 3. 남은 질량들 합
	static int sumLeftM () {
		int left=0;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j].size()==0) continue;
				
				for(int z=0; z<map[i][j].size(); z++) {
					left += map[i][j].get(z)[0];
				}
			}
		}
		return left;
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}