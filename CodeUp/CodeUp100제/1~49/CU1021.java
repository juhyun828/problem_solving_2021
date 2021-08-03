import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// 1021 : [기초-입출력] 단어 1개 입력받아 그대로 출력하기
		
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();
		sc.close();
		System.out.println(word);
	}
}
