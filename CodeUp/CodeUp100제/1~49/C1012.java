import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 1012 : [기초-입출력] 실수 1개 입력받아 그대로 출력하기
		float num;
		Scanner sc = new Scanner(System.in);
		num = sc.nextFloat();
		
		System.out.print(String.format("%.6f",num));
		sc.close();
	}

}

