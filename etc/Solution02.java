import java.util.Scanner;

// 210120
public class Solution02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] ans = new int[T];

		int[] dx = { 0, 0, 0, -1, 1 }; // dummy, 상, 하, 좌, 우
		int[] dy = { 0, -1, 1, 0, 0 };

		for (int tc = 0; tc < T; tc++) {
			int N = sc.nextInt(); // 연못의 크기 N*N
			int SN = sc.nextInt(); // 소금쟁이의 수
			int cnt = SN;

			boolean[][] v = new boolean[N][N]; // 해당 위치에 소금쟁이가 있는지 확인, default : false

			for (int j = 0; j < SN; j++) { // 소금쟁이 정보 저장
				int y = sc.nextInt(); // 행
				int x = sc.nextInt(); // 열
				int d = sc.nextInt(); // 방향

				if (v[y][x] == true) {
					cnt--; // 이미 다른 소금쟁이가 있어서 죽는다.
				} else {
					for (int i = 3; i >= 1; i--) { // 세번 뛴다.
						v[y][x] = false;
						int nx = x + dx[d] * i;
						int ny = y + dy[d] * i;

						if ((nx < 0) || (nx >= N) || (ny < 0) || (ny >= N)) { // 범위를 벗어나면 죽는다.
							cnt--;
							break;
						} else if (v[ny][nx] == true) { // 새 위치에 다른 소금쟁이가 이미 있으면 죽는다.
							cnt--;
							break;
						} else { // 해당 위치로 이동
							x = nx;
							y = ny;
							v[ny][nx] = true;
						}
					}
				}
			}

			ans[tc] = cnt;
		}
		sc.close();

		// 정답 출력
		for (int tc = 0; tc < T; tc++) {
			System.out.println("#" + (tc + 1) + " " + ans[tc]);
		}
	} // end of main
} // end of class
