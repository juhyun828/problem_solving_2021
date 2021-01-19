import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 1025 : [기초-입출력] 정수 1개 입력받아 나누어 출력하기
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();

		for (int i=0; i<str.length(); i++) {
			System.out.print("[" + str.charAt(i));
			for (int j=0; j<str.length()-1-i; j++) {
				System.out.print("0");
			}
			System.out.println("]");
		}
	}
}
