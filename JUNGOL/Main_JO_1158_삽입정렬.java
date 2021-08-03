import java.io.*;
import java.util.*;
// 210516

public class Main_JO_1158_삽입정렬 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = stoi(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			int num = stoi(st.nextToken());
			arr[i] = num;
		}
		
		for(int i=1; i<N; i++) {
			int cur = arr[i];
			int idx = i;
			for(int j=i-1; j>=0; j--) {
				if(arr[j]>=cur) {
					idx = j;
				} 
			}
			
			if(idx!=i) {				
				for(int z=i; z>idx; z--) {
					arr[z] = arr[z-1];
				}
				arr[idx] = cur;
			}

			// print
			for(int z=0; z<arr.length-1; z++) {
				sb.append(arr[z] + " ");
			}
			sb.append(arr[arr.length-1] + "\n");
			
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
