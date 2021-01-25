import java.util.Scanner;

// 210125
// 1055 : [기초-논리연산] 하나라도 참이면 참 출력하기

public class CU1055 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		System.out.println((a==1||b==1)? 1: 0);
		
		sc.close();
	}
}