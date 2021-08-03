// 210201

import java.util.Scanner;

public class Solution_D3_1289_원재의메모리복구하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			char[] arr = sc.next().toCharArray();

			int state = arr[0] - '0';
			int cnt = (state == 1 ? 1 : 0);
			for (int i = 1; i < arr.length; i++) {
				int n = arr[i] - '0';
				if (state != n) {
					cnt++;
					state = n;
				}
			}
			System.out.println("#" + tc + " " + cnt);
		}

	}

}
