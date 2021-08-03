import java.util.Scanner;

public class CU1083 {
	public static void main(String[] args) {
		// 210122
		// 1083 : [기초-종합] 3 6 9 게임의 왕이 되자!
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.close();
		
		for (int i=1; i<=num; i++) {
			if (i % 3 == 0) {
				System.out.print("X ");
			} else {
				System.out.print(i + " ");
			}
		}
		
	} // main
} // class end