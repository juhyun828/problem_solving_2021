import java.util.Scanner;

// 210124
// 1097 : [기초-2차원배열] 바둑알 십자 뒤집기

public class CU1097 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[][] intArr = new int[20][20];
		
		for (int i=1; i<=19; i++) {
			for (int j=1; j<=19; j++) {
				intArr[i][j] = sc.nextInt();
			}
		}
		
		int num = sc.nextInt();
		for (int i=1; i<=num; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			// 가로줄 뒤집기
			for (int col = 1; col<=19; col++) {
				intArr[x][col] = (intArr[x][col] == 0) ? 1: 0;
			}
			
			// 세로줄 뒤집기
			for (int row = 1; row<=19; row++) {
				intArr[row][y] = (intArr[row][y] == 0) ? 1: 0;
			}
			
		}
		
		// 출력
		for (int i=1; i<=19; i++) {
			for (int j=1; j<=19; j++) {
				System.out.print(intArr[i][j] + " ");
			}
			System.out.println();
		}
		
		sc.close();
	}
}