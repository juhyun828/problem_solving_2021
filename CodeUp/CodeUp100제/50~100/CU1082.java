import java.util.Scanner;

public class CU1082 {
	
	public static void main(String[] args) {
		// 1082 : [기초-종합] 16진수 구구단?
		Scanner sc = new Scanner(System.in);
		String xNum = sc.next(); // 16진수 입력
		int dNum = Integer.parseInt(xNum, 16);
		sc.close();
		
		for(int i=1; i<16; i++) {
			String res = String.format("%X", dNum * i);
			String j = String.format("%X", i);
			System.out.println(xNum + "*" + j + "=" + res);
		}

	}

}