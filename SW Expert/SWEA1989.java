import java.util.Scanner;

// 210130
// 1989. 초심자의 회문 검사

public class SWEA1989 {
	
	public static boolean isPalindrome(char[] chArr) {
		int l = chArr.length;
		
		for (int i=0; 2*i<l; i++) {
			if (chArr[i] != chArr[l-i-1]) 
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
		
			char[] chArr = sc.next().toCharArray();
		
			int res = SWEA1989.isPalindrome(chArr) ? 1 : 0;
			System.out.println("#" + tc + " " + res);
			
		} // for

		sc.close();
	} // main
}