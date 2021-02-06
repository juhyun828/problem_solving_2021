// 210206

import java.io.*;
import java.util.*;

public class Solution_D3_1234_비밀번호 {
	
	static int N;
	
	static Stack<Character> encrypt(String str) {
		Stack<Character> stack = new Stack<Character>();
		
		for (int i=0; i<N; i++) {
			if (stack.isEmpty()) {
				stack.push(str.charAt(i));
			}
			
			else if (stack.peek() == str.charAt(i)) {
				stack.pop();
			} else {
				stack.push(str.charAt(i));
			}
		}
		
		return stack;
	}
	
	static String reverse(Stack<Character> stack, int tc) {
		Stack<Character> stack2 = new Stack<Character>();
		while (!stack.isEmpty()) {
			stack2.push(stack.pop());
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("#" + tc + " ");
		while (!stack2.isEmpty()) {
			sb.append(stack2.pop());
		}

		return sb.toString();
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc=1; tc<=10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			
			Stack<Character> pwStack = encrypt(st.nextToken());
			System.out.println(reverse(pwStack, tc));
			
		} // tc

	}

}
