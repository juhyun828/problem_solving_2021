import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 1023 : [기초-입출력] 실수 1개 입력받아 부분별로 출력하기
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] strArr = str.split("\\.");
		System.out.println(strArr[0]);
		System.out.println(strArr[1]);
	}
}

