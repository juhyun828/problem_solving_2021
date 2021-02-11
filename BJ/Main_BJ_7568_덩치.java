import java.io.*;
import java.util.*;
// 210211

public class Main_BJ_7568_덩치 {
	
	static int N;
	static int[][] arr;
	static int[] rank;
	static boolean[] isSelected;
	static int[] numbers;
	
	static void permutation(int cnt, int L) {
		if (cnt==2) {
			
			int n1 = numbers[0];
			int n2 = numbers[1];
			
			if ((arr[n1][0] < arr[n2][0]) && (arr[n1][1] < arr[n2][1])) {
				rank[n1]++;
			}

			return;
		}
		
		if (L >= N) {
			return;
		}
		
		for(int i=0; i<N; i++) {
			if (isSelected[i]) continue;
			
			isSelected[i] = true;
			numbers[cnt] = i;
			permutation(cnt+1, L+1);

			isSelected[i] = false;
		}
	} 
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][2];
		rank = new int[N];
		Arrays.fill(rank, 1);
		isSelected = new boolean[N];
		numbers = new int[2];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken()); // 몸무게
			arr[i][1] = Integer.parseInt(st.nextToken()); // 키
			
		}
		
		permutation(0, 0);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(rank[i] + " ");
		}
		
		System.out.println(sb.toString());
		
		br.close();
	} // main

}
