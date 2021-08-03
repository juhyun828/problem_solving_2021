import java.util.Scanner;

// 210125
// 1057 : [기초-논리연산] 참/거짓이 서로 같을 때에만 참 출력하기

public class Main {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// int -> boolean 
		boolean b1 = sc.nextInt()!=0;
		boolean b2 = sc.nextInt()!=0;
		
		System.out.println((b1^b2)? 0: 1);
		
		sc.close();
	}
}