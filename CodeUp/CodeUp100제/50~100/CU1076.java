import java.util.Scanner;

// 210125
// 1076 : [기초-반복실행구조] 문자 1개 입력받아 알파벳 출력하기

public class CU1076 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char ch = sc.next().charAt(0);
		
		for (char c = 'a'; c<=ch; c++) {
			System.out.print(c + " ");
		}
		sc.close();
	}
}