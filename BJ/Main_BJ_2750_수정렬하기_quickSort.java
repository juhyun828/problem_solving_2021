import java.io.*;
import java.util.*;
// 210516
// 퀵 솔트

public class Main_BJ_2750_수정렬하기_quickSort {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = stoi(br.readLine());
		arr = new int[N];
		for(int i=0; i<N; i++) {
			int num = stoi(br.readLine());
			arr[i] = num;
		}
		
		quickSort(0, N-1);
		StringBuilder sb = new StringBuilder();
		for(int num: arr) sb.append(num+"\n");
		System.out.print(sb.toString());
		br.close();
	}
	
	static void quickSort(int left, int right) {
		if(left>=right) return;
		
		// 분할 (divide)
		int pivot = partition(left, right);
		
		// 피벗을 제외한 2개의 부분 배열 대상으로 순환 호출
		quickSort(left, pivot-1); // 정복(Conquer)
		quickSort(pivot+1, right); // 정복(Conquer)
	}
	
	static int partition(int left, int right) {
		int pivot = arr[left];
		int i = left, j = right;
		
		while(i < j) {
			while(pivot < arr[j]) --j;
			
			while(i < j && pivot >= arr[i]) ++i;
			swap(i, j);
		}
		
		// swap(pivot, left)
		arr[left] = arr[i];
		arr[i] = pivot;

		return i; // pivot의 새 위치
	}
	
	static void swap(int i, int j) {
		int tmp = arr[j];
		arr[j] = arr[i];
		arr[i] = tmp;
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
