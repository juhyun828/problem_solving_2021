import java.util.Scanner;

public class Solution_D2_1954_달팽이숫자 {
	// 210130
	// 1954. 달팽이 숫자

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[][] arr = new int[N][N];

			int[] dx = { 1, 0, -1, 0 };
			int[] dy = { 0, 1, 0, -1 };

			arr[0][0] = 1;

			int num = 2;
			int i = 0;
			int j = 0;
			int d = 0;

			while (num <= N * N) {
				int ny = i + dy[d]; // 세로
				int nx = j + dx[d]; // 가로

				if (ny >= 0 && ny < N && nx >= 0 && nx < N && arr[ny][nx] == 0) {
					arr[ny][nx] = num++;
					i = ny;
					j = nx;
				} else {
					ny = i;
					nx = j;
					d = (d + 1) % 4;
				}
			}

			// 출력
			System.out.println("#" + tc);

			for (i = 0; i < N; i++) {
				for (j = 0; j < N; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}

		}

		sc.close();
	} // main
}