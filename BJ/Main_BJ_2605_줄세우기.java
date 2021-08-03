import java.io.*;
import java.util.*;

// 210221 
public class Main_BJ_2605_줄세우기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		LinkedList<Integer> list = new LinkedList<>();
		list.add(sc.nextInt(), 1);
		if (N > 0) {
			for(int i=2; i<=N; i++) {
				list.add(sc.nextInt(), i);
			}
		}
		
		for(int i=N-1; i>=0; i--) {
			System.out.print(list.get(i) + " ");
		}
		sc.close();
	} 
}
