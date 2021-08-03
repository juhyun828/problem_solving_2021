import java.util.Scanner;

// 210125
// 1067 : [기초-조건/선택실행구조] 정수 1개 입력받아 분석하기

public class CU1067 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		
		if (num < 0) System.out.println("minus");
		else if (num > 0) System.out.println("plus");

		if (num%2==0) System.out.println("even");
		else System.out.println("odd");

		sc.close();
	}
}