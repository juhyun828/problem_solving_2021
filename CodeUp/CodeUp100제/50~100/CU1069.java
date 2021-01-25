import java.util.Scanner;

// 210125
// 1069 : [기초-조건/선택실행구조] 평가 입력받아 다르게 출력하기

public class CU1069 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		
		switch(str) {
			case "A": System.out.println("best!!!"); break;
			case "B": System.out.println("good!!"); break;
			case "C": System.out.println("run!"); break;
			case "D": System.out.println("slowly~"); break;
			default: System.out.println("what?"); break;
		}
		
		sc.close();
	}
}