import java.io.*;
import java.util.*;
// 210517
// 이분탐색

public class Main_BJ_1920_수찾기 {
	static int[] arr;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = stoi(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			arr[i] = stoi(st.nextToken());
		}
		Arrays.sort(arr); // 이분탐색 실행 전 정렬되어 있어야 함
		
		int M = stoi(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<M; i++) {
			int target = stoi(st.nextToken());
			sb.append(binarySearch(N, target)+ "\n");
		}
	
		System.out.println(sb.toString());
		br.close();
	}
	
	static int binarySearch(int N, int key) {
		int start = 0;
		int end = N-1; 
		int mid;
		
		while(start <= end) {
			mid = (start+end)/2;
			
			if(arr[mid]==key) {
				return 1;
			} else if(arr[mid] > key) { // mid보다 크면 왼쪽만 찾기
				end = mid-1;
			} else { // mid보다 작으면 오른쪽만 찾기
				start = mid + 1;
			}
		}
 		
		return 0; // 찾지 못함
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
