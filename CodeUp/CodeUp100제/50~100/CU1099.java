import java.util.Scanner;

// 210125
// 1099 : [기초-2차원배열] 성실한 개미

public class CU1099 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[][] myMap = new int[11][11];
		
		for (int i=1; i<=10; i++) {
			for (int j=1; j<=10; j++) {
				myMap[i][j] = sc.nextInt();
			}
		}
		
		// 이동
		int i = 2; 
		int j = 2;
		
		while ((i < 10) && (j < 10)) {
			
			if (myMap[i][j] == 2) {
				myMap[i][j] = 9;
				break; // 먹이를 찾음
			} else if (myMap[i][j] == 0) {
				myMap[i][j] = 9;
			}
			
			if (myMap[i][j+1] != 1) j += 1;
			else if (myMap[i+1][j] != 1) i += 1;
			else break; // 끝까지 탐색
			
		} // while
				
		
		// 출력
		i = 1; 
		j = 1;
		for (i=1; i<=10; i++) {
			for (j=1; j<=10; j++) {
				System.out.print(myMap[i][j] + " ");
			}
			System.out.println();
		}
		
		sc.close();
	}
}