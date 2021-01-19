import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 1015 : [기초-입출력] 실수 입력받아 둘째 자리까지 출력하기
		
		Scanner sc = new Scanner(System.in);
		float num = sc.nextFloat();
		sc.close();
		System.out.printf("%.2f", num);
	}
}

