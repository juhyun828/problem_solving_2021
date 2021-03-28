import java.util.Scanner;

// 210130
// 2005. 파스칼의 삼각형

public class Solution_D2_2005_파스칼의삼각형 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[][] arr = new int[N + 1][N + 1];

			arr[1][1] = 1;

			for (int i = 2; i <= N; i++) {
				for (int j = 1; j <= i; j++) {
					arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
				}
			}

			// 출력
			System.out.println("#" + tc);
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= i; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}

		} // for

		sc.close();
	} // main
}