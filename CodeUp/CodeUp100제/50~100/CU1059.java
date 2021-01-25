import java.util.Scanner;

// 210125
// 1059 : [기초-비트단위논리연산] 비트단위로 NOT 하여 출력하기

public class CU1059 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		
		System.out.println(~num);
		
		sc.close();
	}
}