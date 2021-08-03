import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 1014 : [기초-입출력] 문자 2개 입력받아 순서 바꿔 출력하기
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		char[] charArray = str.toCharArray();
		System.out.println(charArray[2] + " " + charArray[0]);
		sc.close();
		
	}

}

