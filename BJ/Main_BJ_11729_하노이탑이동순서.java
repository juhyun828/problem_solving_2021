import java.io.*;
import java.util.*;
// 210211

public class Main_BJ_11729_하노이탑이동순서 {
	
	static StringBuilder sb;
	
	static void hanoi(int n, int from, int to, int temp) {
		if (n == 0) {
			return;
		}
		
		hanoi(n-1, from, temp, to);
		sb.append((from + " " + to) + "\n");
		hanoi(n-1, temp, to, from);

	}
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		
		int num = sc.nextInt();
		sb.append(((int)(Math.pow(2, num)-1) + " \n"));

		hanoi(num, 1, 3, 2);
		
		System.out.println(sb.toString());
		sc.close();
	}
}
