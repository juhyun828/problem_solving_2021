import java.util.Scanner;

// 210125
// 1098 : [기초-2차원배열] 설탕과자 뽑기

public class CU1098 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int h = sc.nextInt(); // 세로
		int w = sc.nextInt(); // 가로
	
		int n = sc.nextInt(); // 막대 개수
		
		int[][] myMap = new int[h+1][w+1];
		
		for (int i=1; i<=n; i++) {
			int l = sc.nextInt(); // 길이
			int d = sc.nextInt(); // 방향 0->우, 1-> 하
			int x = sc.nextInt(); // x
			int y = sc.nextInt(); // y

			for (int j=0; j<l; j++) { // 길이만큼
				if (d == 0) {
					myMap[x][y+j] = 1;
				} else if (d == 1) {
					myMap[x+j][y] = 1;
				}
			}
		}
		
		// 출력
		for (int i=1; i<=h; i++) {
			for (int j=1; j<=w; j++) {
				System.out.print(myMap[i][j] + " ");
			}
			System.out.println();
		}
		
		sc.close();
	}
}