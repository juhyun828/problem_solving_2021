import java.io.*;
import java.util.*;
// 210325

public class Solution_D6_1263_사람네트워크2 {
	static int N;
	static int[][] adjMatrix;
	static final int INF = 987654321;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_D6_1263_사람네트워크2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = stoi(st.nextToken());
			adjMatrix = new int[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					adjMatrix[i][j] = stoi(st.nextToken());
					if(i!=j && adjMatrix[i][j]==0) adjMatrix[i][j] = INF;
				}
//				System.out.println(Arrays.toString(adjMatrix[i]));
			}
			
			for(int k=0; k<N; ++k) {
				for(int i=0; i<N; ++i) {
					if(i==k) continue; // 출발지와 경유지가 같다면 다음 출발지
					for(int j=0; j<N; ++j) {
						if(i==j || k==j) continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
						if(adjMatrix[i][j] > adjMatrix[i][k]+adjMatrix[k][j]) {
							adjMatrix[i][j] = adjMatrix[i][k]+adjMatrix[k][j];
						}
					}
				}
			}
			sb.append("#" + tc + " " + find() + "\n");
		} // tc
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static int find() {
		int min = Integer.MAX_VALUE;
		int minVertex = -1;
		int sum=0;
		for(int i=0; i<N; i++) {
			sum = 0; 
			for(int j=0; j<N; j++) {
				sum += adjMatrix[i][j];
			}
			min = Math.min(min, sum);
		}
		return min;
	}

}
