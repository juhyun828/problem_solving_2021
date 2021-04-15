import java.io.*;
import java.util.*;
// 210415

public class Main_BJ_20056_마법사상어와파이어볼 {
	static int N, M, K;
	// 0 1 2 3 4 5 6 7
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static ArrayList<int[]>[][] list; // fireballs 3차원으로 저장-> m, s, d

	// 1.
	static ArrayList<int[]>[][] move() {
		ArrayList<int[]>[][] tmplist = new ArrayList[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				tmplist[i][j] = new ArrayList<int[]>();
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(list[i][j].size()==0) continue; 
				// 파이어 스톰이 있음
				for(int k=0; k<list[i][j].size(); k++) {
					int[] firestrom = list[i][j].get(k);
					// 이동
					int d = firestrom[2];
					int nr = i + dr[d] * firestrom[1];
					int nc = j + dc[d] * firestrom[1];
				
					while(!(nr>=1 && nr<=N && nc>=1 && nc<=N)) {	
						if(nr>=N+1) nr-=N;
						else if(nr<=0) nr+=N;
						if(nc>=N+1) nc-=N;
						else if(nc<=0) nc+=N;
					}
					
					// 추가
					tmplist[nr][nc].add(new int[] {firestrom[0], firestrom[1], firestrom[2]});
				}

			}
		}
		return tmplist;
	
	} // move
	
	// 2. 
	static void divide() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(list[i][j].size()>=2) {
					int mSum=0;
					int sSum=0;
					int dModSum=0; // 2로 나눈 값 나머지의 합이 0이거나 사이즈랑 같으면 모두 짝수/홀수
								// 아니면 섞인것
					
					for(int k=0; k<list[i][j].size(); k++) {
						mSum+=list[i][j].get(k)[0];
						sSum+=list[i][j].get(k)[1];
						dModSum+=list[i][j].get(k)[2]%2;
					}
					int na=mSum/5;
					int ns=sSum/list[i][j].size();
					boolean even = (dModSum==0 || dModSum==list[i][j].size()) ? true : false;
					list[i][j] = new ArrayList<int[]>();
					
					if(na<=0) {
						// 소멸되어 사라짐
						continue;
					}
					
					if(even) {
						for (int d = 0; d <= 6; d += 2) {
							list[i][j].add(new int[] {na, ns, d});
						}
					} else {
						for (int d = 1; d <= 7; d += 2) {
							list[i][j].add(new int[] {na, ns, d});
						}
					}
				}
			}
		}
	}
	
	static int sum() {
		int mSum = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(list[i][j].size()==0) continue; 
				for(int k=0; k<list[i][j].size(); k++) {					
					mSum+=list[i][j].get(k)[0];				
				}
			}
		}
		return mSum;
	}
	
	static void solve() {
		for(int t=1; t<=K; t++) {
			list = move();
			divide();
		}
		
		System.out.println(sum());
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_20056_마법사상어와파이어볼.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken()); // 지도 크기
		M = stoi(st.nextToken()); // fireball 개수
		K = stoi(st.nextToken()); // 시간
		
		list = new ArrayList[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				list[i][j] = new ArrayList<int[]>();
			}
		}

		int r, c, m, s, d;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			r = stoi(st.nextToken());
			c = stoi(st.nextToken());
			m = stoi(st.nextToken());			
			s = stoi(st.nextToken());
			d = stoi(st.nextToken());
			
			list[r][c].add(new int[] {m, s, d});
		}
		
		solve();
		br.close();
	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}