import java.util.Scanner;

// 210125
// 1058 : [기초-논리연산] 둘 다 거짓일 경우만 참 출력하기

public class CU1058 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// int -> boolean 
		boolean b1 = sc.nextInt()!=0;
		boolean b2 = sc.nextInt()!=0;
		
		// not A and not B => not (A or B)
		System.out.println(!(b1||b2)? 1: 0);
		
		sc.close();
	}
}