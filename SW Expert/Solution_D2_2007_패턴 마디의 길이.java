import java.util.Scanner;

public class Solution_D2_2007_패턴 마디의 길이 {
	// 210129
	// 2007. 패턴 마디의 길이
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		
		int N = sc.nextInt();
		
		for (int tc = 1; tc<=N; tc++) {
			char[] arr = sc.next().toCharArray();
			
			int j=1; // 길이는 1부터 시작
			for (int i=0; i<arr.length-1; i++) {
				// arr[0] == arr[j]
				// arr[1] == arr[j+1] 
				// .....
				for(; j<arr.length-1-i; j++) {
					if (arr[i] == arr[i+j]) {
						break;
					}
				}
			}
			
			System.out.println("#" + tc + " " + j);
		} // for
		
		sc.close();	
	} // main
}