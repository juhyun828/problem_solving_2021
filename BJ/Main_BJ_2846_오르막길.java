import java.io.*;
import java.util.*;
// 210520

public class Main_BJ_2846_오르막길 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = stoi(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());		
		for(int i=0; i<N; i++) {
			arr[i] = stoi(st.nextToken());
		}
		
		int start = arr[0], before = arr[0];
		int size = 0;
		int max = 0;
		
		for(int i=1; i<N; i++) {
			int cur = arr[i];
			
			if(before < cur) {
				size = cur-start;
				max = Math.max(max, size);
				before = cur;
				
			} else {
				size = 0;
				start = cur;
				before = cur;
			}
			
		}
		
		System.out.println(max);

		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
