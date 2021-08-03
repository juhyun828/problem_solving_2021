import java.util.Scanner;

// 210126
// 1411 : 빠진 카드

public class CU1411 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		boolean[] v = new boolean[N+1];
		
		for (int i=0; i<N-1; i++) {
			v[sc.nextInt()] = true;
		}
		
		for (int i=1; i<=N; i++) {
			if (!v[i]) System.out.println(i);
		}
		
		sc.close();
	}
}