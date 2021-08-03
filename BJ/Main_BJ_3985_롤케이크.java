import java.io.*;
import java.util.*;

// 210214
public class Main_BJ_3985_롤케이크 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt();
		int N = sc.nextInt();
		
		int[] cake = new int[L+1];
		
		int max1 = 0; // 많이 받을 것으로 기대되는 사람
		int idx1 = -1; // 실제로 많이 받은 사람
		int max2 = 0;
		int idx2 = -1;
		for(int i=1; i<=N; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			if ((e-s)>max1) {
				max1 = e-s;
				idx1 = i;
			} 
			int l = 0;
			for(int j=s; j<=e; j++) {
				if (cake[j]==0) {
					++l;
					cake[j] = i;
				}
			}
			if (l>max2) {
				max2 = l;
				idx2 = i;
			} 
			
		}
		System.out.println(idx1);
		System.out.println(idx2);
		sc.close();
	}
}
