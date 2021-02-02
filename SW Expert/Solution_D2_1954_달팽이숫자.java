//210102
import java.io.*;
import java.util.*;

public class Solution_D2_1954_달팽이숫자 {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		for (int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			int[][] arr = new int[N+1][N+1];
			
			int d = 0;	
			
			arr[1][1] = 1;
			
			int r = 1;
			int c = 1;
			int num = 2;
			
			while (num<= N*N) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위
				if (nr>0 && nr<=N && nc>0 && nc<=N && arr[nr][nc]==0) {
					arr[nr][nc] = num++;
					r = nr;
					c = nc;
				} else {
					d = (d+1) % 4;
				}
			} // while
			
			System.out.println("#" + tc);
			// 출력
			for (r=1; r<=N; r++) {
				for (c=1; c<=N; c++) {
					System.out.print(arr[r][c] + " ");
				}
				System.out.println();
			}

		}
	}
}
