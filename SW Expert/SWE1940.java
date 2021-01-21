import java.util.Scanner;

public class SWEA1940 {
	// 210121
	// 1940. 가랏! RC카!
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] ans = new int[T];
		
		for (int tc=0; tc<T; tc++) {
			int N = sc.nextInt();
			
			int s = 0;
			int v = 0;
			for (int i=0; i<N; i++) {
				int choice = sc.nextInt();
				
				if (choice == 0) {
					s += v;
				}
				else if (choice == 1) { // 가속
					v += sc.nextInt();
					s += v;
				} else if (choice == 2) { // 감속
					int nv = sc.nextInt();
					if (v > 0) {
						v -= nv;
					}
					s += v;
				}
				
			}// for
			ans[tc] = s;
		}// for
		
		for (int tc=0; tc<T; tc++) {
			System.out.println("#" + (tc+1) + " " + ans[tc]);
		}
	}
}