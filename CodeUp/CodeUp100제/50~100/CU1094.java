import java.util.Scanner;

// 210124
// 1094 : [기초-1차원배열] 이상한 출석 번호 부르기2

public class CU1094 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		int[] stuArr = new int[num];
		
		for (int i = 0; i < num; i++) {
			stuArr[i] = sc.nextInt();
		}
		
		for (int i = num -1; i>=0; i--) {
			System.out.print(stuArr[i] + " ");
		}
		
		sc.close();
	}
}