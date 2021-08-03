import java.io.*;
import java.util.*;

// 210208 

public class Solution_D3_9229_한빈이와SpotMart {
	
	static int N, M;
	static int[] snack;
	static int maxWeight;

	static void combination(int cnt, int L, int total) {
		if (total > M) {
			return;
		}
		
		if (cnt == 2) {
			maxWeight = Math.max(maxWeight, total);
			return;
		} 
		
		if (L == N) {
			return;
		}
		
		combination(cnt+1, L+1, total + snack[L]);
		combination(cnt, L+1, total);
	} // 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken()); // 개수
			M = Integer.parseInt(st.nextToken());// 최대 무대
			
			snack = new int[N];
			maxWeight = 0;
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}

			combination(0, 0, 0); 
			if (maxWeight == 0) maxWeight = -1;
			System.out.println("#" + tc + " " + maxWeight);
		} // for
		
		br.close();
	} // main
} 