import java.io.*;
import java.util.*;
// 210325

public class Solution_D3_3307_최장증가부분수열 {
	static int N;
	static int[] arr, LIS;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_D3_3307_최장증가부분수열.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = stoi(br.readLine());
			arr = new int[N];
			LIS = new int[N];
			Arrays.fill(LIS, 1);
			
			// 입력
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				arr[i] = stoi(st.nextToken());
			}
		
			int max = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<=i-1; j++) {
					if(arr[j] < arr[i] && LIS[i] < 1 + LIS[j]) {
						LIS[i] = 1 + LIS[j];
					}
				}
				if(max < LIS[i]) max = LIS[i];
			}
			
			sb.append("#" + tc + " " + max + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
