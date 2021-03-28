import java.util.Scanner;

// 210127
// 1146 : 선택정렬

public class Main_JO_1146_선택정렬 {
	
	public static void printList(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}

		int min;
		int idx;
		for (int i=0; i<N-1; i++) {
			min = arr[i];
			idx = i;
			for (int j=i+1; j<N; j++) {
				if (min>arr[j]) {
					min = arr[j];
					idx = j;
				}
			}
			int tmp = arr[idx];
			arr[idx] = arr[i];
			arr[i] = tmp;
			// 출력
			printList(arr);
		}
		
		sc.close();
	} // main

}