import java.util.Scanner;

// 210125
// 1075 : [기초-반복실행구조] 정수 1개 입력받아 카운트다운 출력하기2

public class CU1075 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		for (int i=num-1; i>=0; i--) {
			System.out.println(i);
		}
		sc.close();
	}
}