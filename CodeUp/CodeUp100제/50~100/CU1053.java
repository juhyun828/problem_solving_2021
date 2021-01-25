import java.util.Scanner;

// 210125
// 1053 : [기초-논리연산] 참 거짓 바꾸기

public class CU1053 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		System.out.println((num==1? 0: 1));
		
		sc.close();
	}
}