import java.io.*;
import java.util.*;

//210208

public class Solution_D4_1494_사랑의카운슬러 {

	static int N; 
	static int[][] map;
	static long min;
	static boolean[] isSelected;

	static void combination(int cnt, int start) {
		if (cnt == N/2) { // 백터 계산

			long[] v1 = {0, 0};
			long[] v2 = {0, 0};
			
			for(int i=0; i<N; i++) {
				if(isSelected[i]) {
					v1[0] += map[i][0]; // x좌표
					v1[1] += map[i][1]; // y좌표
				} else {
					v2[0] += map[i][0]; // x좌표
					v2[1] += map[i][1]; // y좌표
				}
			}
			
			long vX = v1[0] - v2[0];
			long vY = v1[1] - v2[1];
			long v = vX * vX + vY * vY;
			min = Math.min(min, v);

			return; 
		}
		
		for(int i=start; i<N; i++) {
		// 조합은 i가 start부터 시작하는거 까먹지좀 말자 
			 if (isSelected[i]) continue;
			 isSelected[i] = true;
			 combination(cnt+1, i+1);
			 isSelected[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][2];
			isSelected = new boolean[N];

			min = Long.MAX_VALUE; // 최대값 초기화를 이렇게 하는구나
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				map[i][0] = Integer.parseInt(st.nextToken()); // x 좌표 
				map[i][1] = Integer.parseInt(st.nextToken()); // y 좌표 
			}
			
			combination(0, 0);
			System.out.println("#" + tc + " "+ min);
			
		}

		br.close();
	}

}
