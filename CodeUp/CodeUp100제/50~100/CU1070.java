import java.util.Scanner;

// 210125
// 1070 : [기초-조건/선택실행구조] 월 입력받아 계절 출력하기

public class CU1070 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int month = sc.nextInt();
		
		if (month==12 || month==1 || month==2) System.out.println("winter");
		else if (month==3 || month==4 || month==5) System.out.println("spring");
		else if (month==6 || month==7 || month==8) System.out.println("summer");
		else if (month==9 || month==10 || month==11) System.out.println("fall");
		
		sc.close();
	}
}