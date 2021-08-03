import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// 1020 : [기초-입출력] 주민번호 입력받아 형태 바꿔 출력하기
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		String[] strArr = str.split("-");
		System.out.println(strArr[0] + strArr[1]);
		
	}
}

