import java.util.Scanner;
// 210131
// 1984. 중간 평균값 구하기

public class SWEA1984 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 0; tc <= T; tc++) {

			String[] arr = sc.nextLine().split(" ");
			if (tc == 0)
				continue;

			int sum = 0;
			int min = 10001;
			int max = 0;
			for (String s : arr) {
				int num = Integer.parseInt(s);
				sum += num;
				if (max < num)
					max = num;
				if (min > num)
					min = num;
			}

			sum -= max;
			sum -= min;
			float avg = (float) sum / 8;
			int avg2 = sum / 8;

			int res = 0;
			if ((avg + 0.5) >= avg2 + 1) {
				res = avg2 + 1;
			} else {
				res = avg2;
			}

			System.out.print("#" + tc + " " + res + "\n");
		} // for

		sc.close();
	} // main
}