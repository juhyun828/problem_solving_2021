import java.io.*;
import java.util.*;

// 210208 

public class Solution_D3_5215_햄버거다이어트 {
	
	static int N, L;
	static int[][] burgerArr;
	static int maxPoint; 
	
	static void subset(int cnt, int totalCal, int totalPoint) {
		
		if (totalCal > L) {
			return;
		}
		
		if (cnt==N) {
			maxPoint = Math.max(maxPoint, totalPoint);	
			return;
		} 
		subset(cnt+1, totalCal + burgerArr[cnt][1], totalPoint + burgerArr[cnt][0]);
		subset(cnt+1, totalCal, totalPoint);

	}
	 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 재료수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			maxPoint = 0;
		
			burgerArr = new int[N][2];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				burgerArr[i][0]  = Integer.parseInt(st.nextToken()); // 점수
				burgerArr[i][1]  = Integer.parseInt(st.nextToken()); // 칼로리
			}
			
			subset(0, 0, 0);
			System.out.println("#" + tc + " " + maxPoint);
			
		}
		
		br.close();
	}

}
