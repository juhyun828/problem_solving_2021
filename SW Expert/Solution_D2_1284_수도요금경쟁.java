import java.util.Scanner;

// 210130
// 1284. 수도 요금 경쟁
public class Solution_D2_1284_수도요금경쟁 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int P = sc.nextInt();
			int Q = sc.nextInt();
			int R = sc.nextInt();
			int S = sc.nextInt();
			int W = sc.nextInt();
			
			int A = W * P;
			int B;
			if (W <= R) B = Q;
			else B = Q + (W-R) * S;
			
			int res = (A<B ? A : B);
			// 출력
			System.out.println("#" + tc + " " + res);
		
		} // for

		sc.close();
	} // main
}