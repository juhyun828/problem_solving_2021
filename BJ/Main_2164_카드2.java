import java.io.*;
import java.util.*;

// 210208

public class Main_2164_카드2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		// LinkedList<E> 보다 조금 더 빠르다.
		
		for(int i=1; i<=N; i++) {
			queue.offer(i);
		}
		
		while(queue.size() != 1) {
			
			queue.poll(); // 알고리즘에서는 add(), remove() 쓰지 말기
			queue.offer(queue.poll());
		}
		
		System.out.println(queue.poll());
		
	}
}
