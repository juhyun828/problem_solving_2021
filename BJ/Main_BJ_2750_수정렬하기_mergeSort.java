import java.io.*;
import java.util.*;
// 210516
// 합병 정렬

public class Main_BJ_2750_수정렬하기_mergeSort {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = stoi(br.readLine());
		arr = new int[N];
		for(int i=0; i<N; i++) {
			int num = stoi(br.readLine());
			arr[i] = num;
		}
		
		mergeSort(0, N-1);
		
		StringBuilder sb = new StringBuilder();
		for(int num: arr) sb.append(num+"\n");
		System.out.print(sb.toString());
		br.close();
	}
	
	static void mergeSort(int left, int right) {
		if(left<right) {
			int mid = (left+right)/2;
			
			mergeSort(left, mid);
			mergeSort(mid+1, right);
			merge(left, mid, right);
		}
	}
	
	static void merge(int left, int mid, int right) {
		int[] L = Arrays.copyOfRange(arr, left, mid+1);
		int[] R = Arrays.copyOfRange(arr, mid+1, right+1);
		
		int i=0, j=0, k=left;
		int ll = L.length, rl = R.length;
		
		while(i<ll && j < rl) {
			if(L[i] <= R[j]) {
				arr[k++] = L[i++];
			} else {
				arr[k++] = R[j++];
			}
		}
		
		while(i<ll) {
			arr[k++] = L[i++];
		}
		
		while(j<rl) {
			arr[k++] = R[j++];
		}
		
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
