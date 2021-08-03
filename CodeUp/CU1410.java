import java.util.Scanner;

// 210125
// 1410 : 올바른 괄호 1 (괄호 개수 세기)

public class CU1410 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		
		int l = 0;
		int r = 0;
		for (int i=0; i<str.length(); i++) {
			if (str.charAt(i) == '(') l++;
			else r++;
		}
		
		System.out.println(l + " " + r);
		sc.close();
	}
}