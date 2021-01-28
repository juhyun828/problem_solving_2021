import java.util.Scanner;

// 210128
// 1859. 백만 장자 프로젝트
public SWEA1859 Solution {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		
		int T = sc.nextInt();
		for (int tc=0; tc<T; tc++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			
			for(int i=0; i<N; i++) {
				arr[i] = sc.nextInt();
			}
			
			int max = arr[N-1];
			long total = 0;
			for(int i=N-2; i>=0; i--) {
				if (max >= arr[i]) total += (max - arr[i]);
				else max = arr[i];
			}
			
			System.out.println("#" + (tc+1) + " " + total);
		} // for
		
	} // main
}