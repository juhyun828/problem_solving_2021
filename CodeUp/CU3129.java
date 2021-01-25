import java.util.Scanner;

// 210126
// 3129 : 올바른 괄호 2

public class CU3129 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] str = sc.next().split("");
		
		int top = -1;
		boolean flag = false;
		for (String s : str) {
			char c = s.charAt(0);
			if (c=='(') top++;
			else if (c==')') top--;
			
			if (top == -2) {
				flag = true;
				break;
			}
		}
		
		if (!flag && top == -1) System.out.println("good");
		else System.out.println("bad");

		sc.close();
	}
}