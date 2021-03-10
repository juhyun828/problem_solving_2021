import java.io.*;
import java.util.*;
// 210311

public class Main_BJ_17281_야구_fail {
	
	static int N; // 이닝 수
	static int[][] inputArr; // 각 선수가 이닝에서 얻는 결과
	static int[] ord; // 타순 순서 말고 점수를 저장하면 되네
	static boolean[] visited;
	static int outCnt, j, max, state[];
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void permutation(int cnt) {
		if(cnt>9) {
			//System.out.println(Arrays.toString(ord));
			// 게임 시작
			int res = startGame();
			System.out.println(res);
			max = Math.max(max,res );
			return;
		}
		
		for(int i=1; i<=9; i++) {
			if(cnt==4) {
				 permutation(cnt+1);
				 continue;
			}
			if(visited[i]) continue;
		
			visited[i] = true;
			ord[cnt] = i; // cnt번째 타순에 i번째 선수
			permutation(cnt+1);
			visited[i] = false;
			
		}
	}
	
	static int startGame() {
		j=1; outCnt=0; 
		int homeRun=0;
		state = new int[4];
		String[] str = {"아웃", "1루", "2루", "3루", "홈런"};
		
		for(int i=1; i<=N; i++) {// i번째 이닝
			while(true) { // j번째 타순의 선수

				int playerId = ord[j];
				int curPoint = inputArr[N][playerId]; // 현재 이닝 결과
				
				if(curPoint==0) ++outCnt; // 아웃
				else if (curPoint==4) { // 홈런
					++homeRun; // 친 사람 홈런
					for(int z=1; z<=3; z++) {
						homeRun += state[z]; // 루에 있던 모든 선수를 홈런으로 옮긴다.
						state[z] = 0;
					}
				} else {
					int[] newState = new int[4];
					// 원래 루를 비우고 -> curPoint만큼 움직인다.
					for(int z=1; z<=3; z++) {
						// z루의 선수를 -> z+curPoint루로 옮긴다.
						int nz = z+curPoint;
						if(nz>=4) {
							homeRun += state[z];
							state[z]=0;
						}else {
							newState[nz]=state[z];
							state[z]=0;
						}
					} // 
					// System.arraycopy(newState, 1, state, 1, 3);
					for(int z=1; z<=3; z++) {
						state[z]=newState[z];
					}
					// 친 사람도 curPoint루로 이동
					state[curPoint]++;
				}// 
				
				if(outCnt==3) {
					++j;
					state=new int[4];
					break;
				}
				if(j==9) j=1; 
				else ++j;
			}// while
		}// for
		return homeRun;
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		inputArr = new int[N+1][10]; // 0-dummy, 이닝과 선수는 1번부터
		
		StringTokenizer st;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=9; j++) {				
				inputArr[i][j] = stoi(st.nextToken());
			}
		}
		visited = new boolean[10];
		ord = new int[10]; // 0-dummy
		ord[4] = 1; // 네번째 타순은 1번으로 고정됨
		visited[1] = true;
		
		max = 0;
		permutation(1);
		br.close();
	}
}
