import java.io.*;
import java.util.*;

// 210205 

public class Solution_D4_1223_계산기2 {
	
	static int N;
	
	static String toPostfix(String inputStr) {
		StringBuilder sb = new StringBuilder();
		Stack<Character> opStack = new Stack<Character>();

		for (int i = 0; i < N; i++) {
			char cur = inputStr.charAt(i);

			if (cur == '+' || cur == '-') {
				// + -는, 앞에 오는 모든 연산자보다 우선 순위가 낮다.
				// 따라서 스택이 빌 때 까지 먼저 왔던 연산자를 빼준다.
				while (!opStack.isEmpty()) {
					sb.append(opStack.pop());
				}
				opStack.push(cur);
			} else if (cur == '*' || cur == '/') {
				// * / 는, 앞에 오는 * / 연산자보다 우선 순위가 낮다.
				if (! opStack.isEmpty()) {
					while(true) {
						if (opStack.isEmpty() || 
								opStack.peek()=='+' || opStack.peek() == '-') {
							break;
						}
						sb.append(opStack.pop());
					}			
				}
				opStack.push(cur);
			} else {
				// 숫자는 그냥 출력한다.
				sb.append(cur);
			}
		}
		// 스택에서 나오지 않은 연산자를 빼준다.
		while (! opStack.isEmpty()) sb.append(opStack.pop());
		return sb.toString();
	}
	
	static int calPostfix(String postNums) {
		Stack<Integer> numStack= new Stack<Integer>();
		
		for (int i=0; i<N; i++) {
			char cur = postNums.charAt(i);
			if ((cur-'0')>=0 && (cur-'0')<10) {
				numStack.push(cur-'0');
			} else {
				int a = numStack.pop();
				int b = numStack.pop();
				
				switch (cur) {
					case '*' : numStack.push(a*b); break;
					case '/' : numStack.push(a/b); break;
					case '+' : numStack.push(a+b); break;
					case '-' : numStack.push(a-b); break;
				}
			}
		}
		
		return numStack.pop();
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc=1; tc<=10; tc++) {
			N = Integer.parseInt(br.readLine());
			String inputStr = br.readLine();
			
			System.out.println("#" + tc + " " + calPostfix(toPostfix(inputStr)));
		}

	} // main

}
