import java.io.*;
import java.util.*;
// 210516

public class Main_JO_1146_선택정렬 {
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

		// 최소값을 찾아 맨 앞과 교환한다.
		int min = Integer.MAX_VALUE;
		int minIdx = 0;
		for(int i=0; i<N-1; i++) {
			min = arr[i];
			minIdx = i;
			for(int j=i+1; j<N; j++) {
				if(min>arr[j]) {
					min=arr[j];
					minIdx=j;
				}
			}
			
			if(min!=arr[i]) {
				arr[minIdx] = arr[i];
				arr[i] = min;	
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
