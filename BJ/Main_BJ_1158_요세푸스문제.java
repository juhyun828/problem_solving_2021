// 210209 

import java.io.*;
import java.util.*;

public class Main_BJ_1158_요세푸스문제 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_1158_요세푸스문제.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer str = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		int N = Integer.parseInt(str.nextToken());
		int K = Integer.parseInt(str.nextToken());
		
		Queue<Integer> queue = new LinkedList<Integer>();

		for(int i=1; i<=N; i++) {
			queue.offer(i);
		}
		
//		int idx = 1;
		
//		while (! queue.isEmpty()) {
//			if (idx % K == 0) {
//				sb.append(queue.poll() + ", ");
//			} else {
//				queue.offer(queue.poll());
//			}
//			idx++;
//		}
		
		while (! queue.isEmpty()) {
			
			for(int i=0; i<K-1; i++) {
				queue.offer(queue.poll());
			}
			
			sb.append(queue.poll() + ", ");
		}
		
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb.toString());
		br.close();
	} // main

}
