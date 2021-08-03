import java.io.*;
import java.util.*;

// 210215

public class Solution_D3_7964_부먹왕국의차원관문 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			int D = sc.nextInt();
			
			int[] arr = new int[N+1];
			for(int i=1; i<=N; i++) {
				arr[i] = sc.nextInt();
			}
			
			int cnt = 0;
			
			for (int i=N+1; i>1; ) {
				for(int j=1; j<=D; j++) {
					if (arr[--i]==1) break;
					if(j==D) {
						arr[i] = 1;
						++cnt;
					}
				}
			}
			System.out.println("#" + tc + " " + cnt);
		}
		sc.close();
	} //
}
