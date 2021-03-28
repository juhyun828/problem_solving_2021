import java.util.Scanner;

// 210131
// 1986. 지그재그 숫자

public class Solution_D2_1986_지그재그숫자 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
		
			int N = sc.nextInt();
			
			int sum = 0;
			
			for (int i = 1; i<=N; i++) {
				sum += (i%2==0 ? -i : i);
			}
			
			System.out.println("#" + tc + " " + sum);
			
		} // for

		sc.close();
	} // main
}