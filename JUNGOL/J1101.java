import java.util.Scanner;

// 210126
// 1101 : 스택 (stack)
// 16MB, 335ms, Success

public class J1101 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] stack = new int[101];
		int top = 0;
		
		
		for (int i=0; i<N; i++) {
			String str = sc.next();
			if (str.equals("i")) { // push
				stack[++top] = sc.nextInt();
			} else if (str.equals("o")) { // pop
				System.out.println((top<=0) ? "empty" : stack[top--]);
			} else {
				System.out.println(top);
			}
		}
		
		sc.close();
	} // main

}