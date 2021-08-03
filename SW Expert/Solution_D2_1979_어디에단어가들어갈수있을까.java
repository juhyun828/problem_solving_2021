//210102

import java.io.*;
import java.util.*;

public class Solution_D2_1979_어디에단어가들어갈수있을까 {

	public static void main(String[] args)  {

		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			int K = sc.nextInt(); // 단어 수
			
			int[][] arr = new int[N+1][N+1];
			
			for (int r=1; r<=N; r++) {
				for(int c=1; c<=N; c++) {
					arr[r][c] = sc.nextInt();
				}
			}
			
			int total = 0;
			
			// 행별로 탐색
			for (int r=1; r<=N; r++) {
				int cnt = 0;
				for(int c=1; c<=N; c++) {
					if (arr[r][c] == 1) cnt++;
					else if (arr[r][c] == 0) {
						if (cnt == K) {
							total++;
						}
						cnt=0;
					}
				}
				
				if (cnt == K) total++;
			} // for
			
			// 열별로 탐색
			for (int c=1; c<=N; c++) {
				int cnt = 0;
				for(int r=1; r<=N; r++) {
					if (arr[r][c] == 1) cnt++;
					else if (arr[r][c] == 0) {
						if (cnt == K) {
							total++;
						}
						cnt=0;
					}
				}
				
				if (cnt == K) total++;
			} // for
			
			System.out.println("#" + tc + " " + total);
		} 
	
		sc.close();
	} // main

}
