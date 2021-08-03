import java.util.Scanner;

// 210125
// 1074 : [기초-반복실행구조] 정수 1개 입력받아 카운트다운 출력하기1

public class CU1074 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		for (int i=num; i>=1; i--) {
			System.out.println(i);
		}
		sc.close();
	}
}