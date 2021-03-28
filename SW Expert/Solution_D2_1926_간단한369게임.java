import java.util.Scanner;

public class Solution_D2_1926_간단한369게임 {
	// 210129
	// 1926. 간단한 369게임

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		for (int i = 1; i <= N; i++) {
			int d = 1;
			if (i >= 10 && i < 100)
				d = 10;
			else if (i >= 100 && i < 1000)
				d = 100;
			else if (i == 1000)
				d = 1000;

			boolean flag = false;
			int tmp = i;
			while (d > 0) {
				int num = tmp / d;
				if (num == 3 || num == 6 || num == 9) {
					flag = true;
					System.out.print("-");
				}
				tmp -= num * d;
				d /= 10;
			} // while
			if (!flag)
				System.out.print(i);
			System.out.print(" ");

		} // for
		sc.close();
	} // main
}