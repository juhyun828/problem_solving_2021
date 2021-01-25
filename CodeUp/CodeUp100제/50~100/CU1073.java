import java.util.Scanner;

// 210125
// 1073 : [기초-반복실행구조] 0 입력될 때까지 무한 출력하기2

public class CU1073 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int num = sc.nextInt();
			if (num==0) break;
			System.out.println(num);
		}
		
		sc.close();
	}
}