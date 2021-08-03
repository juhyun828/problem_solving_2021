import java.util.Scanner;

// 210125
// 1056 : [기초-논리연산] 참/거짓이 서로 다를 때에만 참 출력하기

public class CU1056 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// int -> boolean 
		boolean b1 = sc.nextInt()!=0;
		boolean b2 = sc.nextInt()!=0;
		
		System.out.println((b1!=b2)? 1: 0);
		
		sc.close();
	}
}