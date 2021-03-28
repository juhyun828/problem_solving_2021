import java.util.Scanner;

// 210130
// 2001. 파리 퇴치
public class Solution_D2_2001_파리퇴치 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] arr = new int[N][N];

			// 입력
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			int max = 0;
			for (int i = 0; i <= (N - M); i++) {
				for (int j = 0; j <= (N - M); j++) {
					int sum = 0;
					for (int z=0; z<M; z++) {
						for (int w=0; w<M; w++) {
							sum += arr[i+z][j+w];
						}
					}
					if (sum > max) max = sum;
				}
			}
			System.out.println("#" + tc + " " + max);
		} // for

		sc.close();
	} // main
}