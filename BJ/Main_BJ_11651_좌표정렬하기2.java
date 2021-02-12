import java.io.*;
import java.util.*;
// 210212

public class Main_BJ_11651_좌표정렬하기2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] arr = new int[N][2];
		
		for(int i=0; i<N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) {
					return Integer.compare(o1[0], o2[0]);					
				}
				return Integer.compare(o1[1], o2[1]);
			}
		}) ;
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(arr[i][0] + " " + arr[i][1] + "\n");
		}
		
		System.out.println(sb.toString());

		sc.close();
	} // main

}
