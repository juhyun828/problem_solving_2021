// 210204

import java.io.*;
import java.util.*;

public class Solution_D3_1206_View {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			Integer[] arr = new Integer[N];
			
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int sum = 0;
			for (int i=2; i<N-2; i++) {
				
				int min = 255;
				
				int l1 = arr[i]-arr[i-2];
				if (l1 <= 0) continue;
				else if (min > l1) min=l1;
				
				int l2 = arr[i]-arr[i-1];
				if (l2 <= 0) continue;
				else if (min > l2) min=l2;
				
				int r1 = arr[i]-arr[i+1];
				if (r1 <= 0) continue;
				else if (min > r1) min=r1;
				
				int r2 = arr[i]-arr[i+2];
				if (r2 <= 0) continue;
				else if (min > r2) min=r2;

				sum += min;
			}

			System.out.println("#" + tc + " " + sum);
		}
		
	}
}
