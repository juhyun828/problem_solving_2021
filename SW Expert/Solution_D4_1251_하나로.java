import java.io.*;
import java.util.*;
// 210324

public class Solution_D4_1251_하나로 {
	static int N;
	static double E;
	static int[] x, y;
	static long[][] map;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_D4_1251_하나로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {

			N = stoi(br.readLine());
			x = new int[N];
			y = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				x[i] = stoi(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				y[i] = stoi(st.nextToken());
			}
			E = Double.parseDouble(br.readLine());
			
			// 인접 행렬 만들기
			map = new long[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					long xx = x[i]-x[j];
					long yy = y[i]-y[j];
					map[i][j] = map[j][i] = xx*xx + yy*yy;
				}
			}
		
			// 프림 알고리즘으로 MST 구하기
			long L = prim();
			System.out.println ("#" + tc + " " + Math.round(E*L));
		}
	
		
		br.close();
	}
	
	static long prim() {
		long totalWeight = 0;
		boolean[] v = new boolean[N];
		
		// minEdge
		long[] minEdge = new long[N];
		Arrays.fill(minEdge, Long.MAX_VALUE);
		
		// 시작 정점 거리값 
		minEdge[0] = 0; 

		for(int c=0; c<N; c++) {
			// minEdge에서 최소 비용으로 연결된 정점 찾기 
			long min = Long.MAX_VALUE;
			int minVertext = 0;
			for(int i=0; i<N; i++) {
				if(!v[i] && minEdge[i] < min) {
					min = minEdge[i];
					minVertext = i;
				}
 			}

			totalWeight += min;
			v[minVertext] = true;
			
			// 해당 정점과 인접한 정점을 더 적은 비용으로 방문 가능한지 찾기
			for(int i=0; i<N; i++) {
				if(!v[i] && map[minVertext][i]!=0 && minEdge[i] > map[minVertext][i]) {
					minEdge[i] = map[minVertext][i];
				}
			}

		}
		return totalWeight;
	}
	
}
