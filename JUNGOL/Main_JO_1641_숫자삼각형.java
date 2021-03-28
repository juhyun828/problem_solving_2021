import java.util.Scanner;

// 210126
// 1641 : 숫자삼각형

public class Main_JO_1641_숫자삼각형 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		if (n % 2 == 0 || n > 100) {
			System.out.println("INPUT ERROR!");
		} else {
			if (m == 1) {
				int num = 1;
				for (int i = 0; i < n; i++) {
					// 짝수줄
					if (i % 2 == 0) {
						for (int j = 0; j <= i; j++) {
							System.out.print(num++ + " ");
						}
						System.out.println();
					}
					// 홀수줄
					else {
						int max = num + i;
						for (int j = 0; j <= i; j++) {
							System.out.print(max-- + " ");
							num++;
						}
						System.out.println();
					}
				}
			} // if
			else if (m == 2) {
				int num = 0;
				int cnt = n * 2 - 1;
				for (int i = 0; i < n; i++) {
					// 공백 출력
					for (int j = 0; j < i; j++) {
						System.out.print("  ");
					}
					// 숫자 출력
					for (int j = 0; j < cnt; j++) {
						System.out.print(num + " ");
					}
					num++;
					cnt -= 2;
					System.out.println();

				}
			} // else if
			else if (m == 3) {
				int mid = n / 2;
				for (int i = 0; i < n; i++) {
					if (i <= mid) {
						for (int j = 0; j <= i; j++) {
							System.out.print((j + 1) + " ");
						}
					} else {
						for (int j = 0; j < mid; j++) {
							System.out.print((j + 1) + " ");
						}
						mid--;
					}
					System.out.println();
				}
			} // else if
			else {
				System.out.println("INPUT ERROR!");
			}
		}

		sc.close();
	} // main

}