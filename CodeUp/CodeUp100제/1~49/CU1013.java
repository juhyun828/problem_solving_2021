import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 1013 : [기초-입출력] 정수 2개 입력받아 그대로 출력하기
		int a, b;
		
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		String[] strArr = str.split(" ");
		sc.close();
		a = Integer.parseInt(strArr[0]);
		b = Integer.parseInt(strArr[1]);
		
		System.out.println(a + " "+ b);
	}

}

