import java.io.FileInputStream;
import java.util.Scanner;

// 210203
public class Solution_D2_1486_장훈이의높은선반 {
	
	static int[] arr;
	static int N; 
	static int B;
	static int ans;
	
	private static void recursive(int i, int sum) {
		
		if (i == arr.length) {			
			if (B <= sum) {
				if (ans > sum) {
					ans = sum;
				}
			}
			return;
		}
		recursive(i+1, sum + arr[i]);
		recursive(i+1, sum);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc=1; tc<=T; tc++) {
			N = sc.nextInt(); // 점원 수
			B = sc.nextInt(); // 선반 높이
			arr = new int[N];
			
			ans = 10000 * 20;
			
			for (int i=0; i<N; i++) 
				arr[i] = sc.nextInt();
			recursive(0, 0);
		
			System.out.println("#" + tc + " " + (ans-B));
		}

		sc.close();
	} // main
}
