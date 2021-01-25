import java.util.Scanner;

// 210125
// 1054 : [기초-논리연산] 둘 다 참일 경우만 참 출력하기

public class CU1054 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		System.out.println((a==1&&b==1)? 1: 0);
		
		sc.close();
	}
}