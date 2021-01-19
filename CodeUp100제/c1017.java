import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 1017 : [기초-입출력] 정수 1개 입력받아 3번 출력하기
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.close();
		System.out.printf("%d %d %d", num, num, num);
	}
}
