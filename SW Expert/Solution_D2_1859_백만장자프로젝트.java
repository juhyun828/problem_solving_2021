import java.util.Scanner;

// 210223
// 1859. 백만 장자 프로젝트
public class Solution_D2_1859_백만장자프로젝트 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		
		int T = sc.nextInt();
		for (int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			
			for(int i=0; i<N; i++) {
				arr[i] = sc.nextInt();
			}
			
			int max = arr[N-1];
			long ans = 0;
			for(int i=N-2; i>=0; i--) {
				if (arr[i] > max) max = arr[i];
				else ans += (max-arr[i]); // 같으면 어차피 0
			}
			System.out.println("#"+ tc + " " + ans);
		} // for
		
	} // main
}