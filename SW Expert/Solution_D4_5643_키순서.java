import java.io.*;
import java.util.*;
// 210421

public class Solution_D4_5643_키순서 {
	// 자신의 순위를 알 수 있는 학생 수
	static int N, M;
	static int[][] map;
	static final int INF = 987654321;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_D4_5643_키순서.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			N = stoi(br.readLine().trim());
			M = stoi(br.readLine().trim());
			map = new int[N+1][N+1];
			for(int i=1; i<=N; i++) {
				Arrays.fill(map[i], INF);
			}
			// 대각선
			for(int i=1; i<=N; i++) {
				map[i][i] = 0;
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				int from = stoi(st.nextToken());
				int to = stoi(st.nextToken());;
				map[from][to] = 1;
			}
			
			// 경 출 도
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					if(k==i) continue;
					for(int j=1; j<=N; j++) {
						if(k==j || i==j) continue;
						if(map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
			int res=0;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i==j) continue;
					if(map[i][j]==INF && map[j][i]==INF) {
						++res;
						break;
					}
				}
			}

			int ans = N-res;
			if(ans<0) ans = 0;
			sb.append("#" + tc + " " + ans + "\n");

		} // tc
	
		System.out.println(sb.toString());
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}