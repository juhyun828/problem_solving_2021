import java.io.*;
import java.util.*;
// 210516

public class Main_JO_1157_버블정렬 {
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
		
		for(int end=N-1; end>0; end--) {
			for(int i=0; i<end; i++) {
				if(arr[i]>arr[i+1]) {
					int tmp = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = tmp;
				}
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
