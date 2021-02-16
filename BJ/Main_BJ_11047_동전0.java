import java.io.*;
import java.util.*;

// 210216

public class Main_BJ_11047_동전0 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}

		int cnt = 0;
		int current = K;
		int i = N-1;
		
		while (true) {

			if (arr[i] > K) {
				--i;
				continue;
			}
			
			if (current%arr[i]==0) {
				cnt += current/arr[i];
				current = 0;
				break;
				
			} else {
				while((current-arr[i])>0) {
					++cnt;
					current -= arr[i];
				}
			}
			// System.out.println(arr[i] + ", " + current + ", " + cnt);
			--i;

		}
		
		System.out.println(cnt);
		
		sc.close();
	}
}
