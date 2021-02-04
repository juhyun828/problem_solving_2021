// 210204

import java.io.*;
import java.util.*;

public class Solution_D4_1218_괄호짝짓기 {
	static int N;
	static char[] stack;

	private static boolean isParenthis(char[] arr) {
		int top = -1;
		
		for (int i=0; i<N; i++) {
			char c = arr[i];
			switch (c) {
				// 여는 괄호라면 stack에 저장
				case '(' : stack[++top] = '('; break;
				case '[' : stack[++top] = '['; break;
				case '{' : stack[++top] = '{'; break;
				case '<' : stack[++top] = '<'; break;
				
				// 닫는 괄호라면 stack[top]와 짝인지 비교한다.
				case ')' : {
					if (top < 0 ) return false;
					if (stack[top] ==  '(' ) {
						top--;
					}
					else return false;
					break;
				}
				
				case ']' : {	
					if (top < 0 ) return false;
					if (stack[top] == '[') {
						top--;
					}
					else return false;
					break;
				}
				
				case '}' : {
					if (top < 0 ) return false;
					if (stack[top] == '{') {
						top--;
					}
					else return false;
					break;
				}
				
				case '>' : {
					if (top < 0 ) return false;
					if (stack[top] == '<') {
						top--;
					}
					else return false;
					break;
				} 
			} // switch
		} // for
		if (top != -1) return false;
		else return true;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		for(int tc=1; tc<=10; tc++) {
			N = sc.nextInt();
			char[] arr = sc.next().toCharArray();
			stack = new char[N];
			
			int ans = (isParenthis(arr)==true) ? 1 : 0;
			System.out.println("#" + tc + " " + ans);
		}

		sc.close();
	}

}
