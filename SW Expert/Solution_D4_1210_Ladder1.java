//210102
import java.io.FileInputStream;
import java.util.Scanner;

public class Solution_D4_1210_Ladder1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			int tcNum = sc.nextInt();

			int[][] arr = new int[102][102];

			int startRow = 0;
			int startCol = 0;
			int r;
			int c;
			
			for (r = 1; r <= 100; r++) {
				for (c = 1; c <= 100; c++) {
					int num = sc.nextInt();
					arr[r][c] = num;
					if (num == 2) {
						startRow = r;
						startCol = c;
					}
				}
			}

			r = startRow;
			c = startCol;
			
			while (true) {
				if (r == 1) break;
			
				if (arr[r][c-1] == 1 ) {
					// 왼쪽 먼저 탐색
					arr[r][c] = 0;
					c = c-1;
				} else if(arr[r][c+1] == 1) {
					// 오른쪽 탐색
					arr[r][c] = 0;
					c = c+1;
				} else if (arr[r-1][c] == 1 ) {
					// 위쪽 탐색
					arr[r][c] = 0;
					r = r-1;
				}
			}
			
			// 출력
			System.out.println("#" + tc + " " + (c-1));

		} 

	} // main

}
