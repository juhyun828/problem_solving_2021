import java.util.Scanner;

// 210126
// 1412 : 알파벳 개수 출력하기

public class CU1412 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		// String -> char 배열
		char[] chArr = sc.nextLine().toCharArray();
		int[] cntArr = new int['z'-'a'+1];
		
		for (char c : chArr) {
			if (c >= 'a' && c <= 'z') {
				cntArr[c-'a'] += 1;
			}
		}
		
		for (int i=0; i<cntArr.length; i++) {
			char ch = (char)(i+'a');
			System.out.println(ch + ":" + cntArr[i]);
		}
		
	}
}