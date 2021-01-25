import java.util.Scanner;

// 210125
// 1060 : [기초-비트단위논리연산] 비트단위로 AND 하여 출력하기

/* 	비트단위(bitwise)연산자
	~(bitwise not), &(bitwise and), |(bitwise or), ^(bitwise xor),
	<<(bitwise left shift), >>(bitwise right shift) 
*/

public class CU1060 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		System.out.println(a & b);
		
		sc.close();
	}
}