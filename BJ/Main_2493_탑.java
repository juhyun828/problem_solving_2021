// 210204 

import java.io.*;
import java.util.*;

public class Main_2493_탑 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] input = new int[N];
		for (int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] ans =  new int[N];
		Stack<Integer> stack = new Stack<Integer>(); 
		Stack<Integer> index = new Stack<Integer>();  // index 저장

		for (int i=0; i<N; i++) {
			while(true) {
				if (stack.isEmpty()) {
					stack.add(input[i]); index.add(i+1);
					break;
					
				} else {
					int top = stack.pop();
					int topIndex = index.pop();
					
					if (top > input[i]) {
						ans[i] = topIndex;
						stack.add(top); index.add(topIndex);
						stack.add(input[i]); index.add(i+1);
						break;
					}
				}
			}
		} // for
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			sb.append(ans[i] + " ");
		}
		System.out.println(sb.toString());
		
		br.close();
	}

}
