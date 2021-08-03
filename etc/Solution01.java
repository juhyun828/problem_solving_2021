import java.util.Arrays;
import java.util.Scanner;

public class Solution01 {
	// 210120
	public static void main(String[] args) {
		int T;
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		int[] ans = new int[T];
		
		for (int tc=0; tc<T; tc++) {
			int N;
			N = sc.nextInt();
			
			// 상하좌우대각선
			int x[] = {-1, 1, 0, 0, -1, 1, -1, 1};
			int y[] = { 0, 0, -1, 1,-1, 1, 1, -1}; 
			
			char[][] chArr = new char[N][N];
			int[][] intArr = new int[N][N];
			
			char ch;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					ch = sc.next().charAt(0);
					chArr[i][j] = ch;
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					// 현 위치가 B라면
					if (chArr[i][j] == 'B') {
						// 인접한 곳에 G가 있는지 찾는다.
						boolean flag = false;
						for (int z=0; z<8; z++) {
							if ((i + x[z] >=0) & (i + x[z] < N) & 
									(j + y[z] >=0) & (j + y[z] < N)) { // 범위 체크
								if (chArr[i+x[z]][j+y[z]] == 'G') {
									// 인접한 곳에 G가 있다면
									intArr[i][j] = 2; // 해당 위치에 건물 2개 세울 수 있다.
									flag = true;
//									System.out.println("break");
									break;
								}
							}
							
						} // end of for
						if (flag == false) {
//							System.out.println("false");
							// 인접한 곳에 G가 없다면 가로/세로 줄에서 B의 개수를 센다
							int cnt = 0;
							// 가로 줄
							for (int z=0; z<N; z++) {
								if (chArr[i][z] == 'B') cnt += 1;
								if (chArr[z][j] == 'B') cnt +=1;
							}
							intArr[i][j] = (cnt-1);
							
						}
					
				}
			}
			
			int max = 0;
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (max < intArr[i][j]) max = intArr[i][j];
				}
			}
			ans[tc] = max;
		} // end of while
		
		// 정답 출력
		for (int tc=0; tc<ans.length; tc++) {
			System.out.println("#" + (tc+1) + " " + ans[tc]);
		}
	}
}
