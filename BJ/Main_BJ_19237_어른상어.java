import java.io.*;
import java.util.*;
// 210414

public class Main_BJ_19237_어른상어 {
	static int N, M, K;
	static int[][] sharkMap;
	static int[][][] smellMap;
	static int[] dir;
	static int[][][] order;
	static int[] dr = {-1, 1, 0, 0}; // 0 위 1 아래 2 좌 4 우
	static int[] dc = {0, 0, -1, 1};
	
	static void updateSmell() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(smellMap[i][j][1] > 0) {
					smellMap[i][j][1] -= 1;
				}
				// 여기서 상어의 새 위치에 냄새 정보를 업데이트 하기 때문에
				// 상어를 움직일 때 같은 동작을 할 필요 없다.
				if(sharkMap[i][j]>0) {
					smellMap[i][j][0] = sharkMap[i][j];
					smellMap[i][j][1] = K;
				}
			}
		}
	}
	
	static int[][] move() {
		int shark;
		int r, c, d, nr, nc;
		boolean found=false;
		
		int[][] newSharMap = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if (sharkMap[i][j] > 0) {
					// 상어가 있는 칸
					
					// 1) 상하좌우 빈칸 탐색
					shark = sharkMap[i][j];
					r=i; c=j; d=dir[shark];
					found=false;
					for(int di=0; di<4; di++) {
						nr = r+dr[ order[shark][d][di] ];
						nc = c+dc[ order[shark][d][di] ];
						if(nr<0 || nr>=N || nc<0 || nc>=N) continue;

						if(smellMap[nr][nc][1]==0) { // 냄새x
							if(newSharMap[nr][nc]==0) { // 아직 다른 상어가 안옴
								newSharMap[nr][nc]= shark;
								dir[shark] = order[shark][d][di];
								found = true;
								break;
							}
								
							else  {
								// 냄새는 아직 없지만 이미 다른 상어가 들어옴
								newSharMap[nr][nc]= Math.min(shark, newSharMap[nr][nc]);
								dir[shark] = order[shark][d][di];
								found = true;
								break;
							}	
						}
						
					} // 
					
					// 2) 상하좌우 자신의 냄새 칸 탐색
					if(found) continue;
					
					for(int di=0; di<4; di++) {
						nr = r+dr[ order[shark][d][di] ];
						nc = c+dc[ order[shark][d][di] ];
						if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
						if(smellMap[nr][nc][0] == shark) {
							// 자신과 같은 냄새 칸
							dir[shark] = order[shark][d][di];
							newSharMap[nr][nc]= shark;
							break;
						}
					} // for
				}
			}
		}
		return newSharMap;
	}
	
	static boolean check( ) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(sharkMap[i][j]>1) return false;
			}
		}
		return true;
	}
	
	static int solve() {
		int time = 0;
		
		while(true) {
			updateSmell();
			sharkMap = move();
			++time;
			if(check()) return time;
			if(time>=1000) return -1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_19237_어른상어.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		sharkMap = new int[N][N];
		smellMap = new int[N][N][N]; // 세번째: {상어번호, k}
		dir = new int[M+1];
		order = new int[M+1][4][4]; // 상어를 0번부터 시작하면 빈칸과 구분x, 방향 정보는 -1하여 저장
		
		// 상어 정보 & 냄새 정보
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				sharkMap[i][j] = stoi(st.nextToken());
				if(sharkMap[i][j]>0) {
					smellMap[i][j][0] = sharkMap[i][j];
					smellMap[i][j][0] = K;
				}
			}
		}

		// 현재 상어 방향
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=M; i++) {
			dir[i] = stoi(st.nextToken())-1;
		}
		
		// 상어 별 이동 우선순위
		for(int i=1; i<=M; i++) {
			for(int j=0; j<4; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int z=0; z<4; z++) {
					order[i][j][z] = stoi(st.nextToken())-1;
				}
			}
		}
		
		System.out.println(solve());

		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
