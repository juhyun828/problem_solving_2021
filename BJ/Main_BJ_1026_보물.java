import java.io.*;
import java.util.*;
// 210328

public class Main_BJ_1026_보물 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = stoi(br.readLine());
		int[] A = new int[N];
		Integer[] B = new Integer[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			A[i] = stoi(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			B[i] = stoi(st.nextToken());
		}
		
		Arrays.sort(A);
		Arrays.sort(B, Collections.reverseOrder());
		
		int res = 0;
		for(int i=0; i<N; i++) {
			res += A[i] * B[i];
		}
		
		System.out.println(res);

		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
