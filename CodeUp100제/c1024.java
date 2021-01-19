import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 1024 : [기초-입출력] 단어 1개 입력받아 나누어 출력하기
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		
		char[] charArr = str.toCharArray();
		
		for (int i=0; i<charArr.length; i++) {
			System.out.println("\'" + charArr[i] + "\'");
		}
	}
}

