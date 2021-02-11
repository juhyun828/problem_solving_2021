import java.io.*;
import java.util.*;
// 210211

public class Main_BJ_2798_블랙잭 {
	
	static int N, M;
	static int[] arr;
	static int max = 0;
	static boolean[] isSelected;
	
	static void permutation(int cnt, int L, int total) {
		if (total > M) return;
		
		if (cnt == 3) {
			max = Math.max(max, total);
			return;
		}
		
		if (L > N) return;
		
		for(int i=0; i<N; i++) {
			if (isSelected[i]) continue;
			
			isSelected[i] = true;
			permutation(cnt+1, L+1, total+arr[i]);
			
			isSelected[i] = false;
		}
		
	}
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		isSelected = new boolean[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		permutation(0, 0, 0);
		System.out.println(max);
	
		sc.close();
	}
}
