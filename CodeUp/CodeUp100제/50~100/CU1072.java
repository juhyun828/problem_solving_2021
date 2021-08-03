import java.util.Scanner;

// 210125
// 1072 : [기초-반복실행구조] 정수 입력받아 계속 출력하기

public class CU1072 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		for (int i=0; i<num; i++) {
			System.out.println(sc.next());
		}
		
		sc.close();
	}
}