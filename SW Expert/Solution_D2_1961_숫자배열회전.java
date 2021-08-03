// 210209

import java.io.*;
import java.util.*;

public class Solution_D2_1961_숫자배열회전 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[N][N];
			int[][] newArr = new int[N][N];
			int[][] res = new int[N][N*3];
				
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 회전하기
			for(int d=0; d<3; d++) {
				for(int r=0; r<N; r++) {
					for(int nr=0; nr<N; nr++) {
						newArr[nr][N-1-r] = arr[r][nr];
					}
				}
				
				// 배열 update
				for(int r=0; r<N; r++) {
					for(int c=0; c<N; c++) {
						arr[r][c] = newArr[r][c];
						res[r][c+N*d] = newArr[r][c];
					}
				}
			}

			System.out.println("#" + tc);
			for(int r=0; r<N; r++) {
				for (int c=0; c<3*N; c++) {
					if (c>0 && c%N==0) System.out.print(" ");
					System.out.print(res[r][c]);

				}
				System.out.println();
			}

		}
		br.close();
	} // main
}
