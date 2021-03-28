import java.util.Scanner;

public class Solution_D2_1974_스도쿠검증 {
	// 210121
	// 1974. 스도쿠 검증
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] ans = new int[T];
		
		for (int tc=0; tc<T; tc++) {
			int[][] myMap = new int[9][9];
			
			for (int i=0; i<9; i++ ) {
				for (int j=0; j<9; j++) {
					myMap[i][j] = sc.nextInt();
				}
			}
			
			boolean flag = false;
			// 행 탐색
			for (int row=0; row<9; row++) {
				if (flag) {
					break;
				}
				
				boolean[] v = new boolean[10];
				for (int col=0; col<9; col++) {
					int cur = myMap[row][col];
					if (v[cur]) { // 숫자 중복
						flag = true;
						break;
					} else {
						v[cur] = true;
					}
				}
			} 
			
			if (! flag) { // 행 탐색에서 중복을 발견하지 않으면 열 탐색
				for (int col=0; col<9; col++) {
					if (flag) {
						break;
					}
					
					boolean[] v = new boolean[10];
					for (int row=0; row<9; row++) {
						int cur = myMap[row][col];
						if (v[cur]) { // 숫자 중복
							flag = true;
							break;
						} else {
							v[cur] = true;
						}
					}
				}
			}

			// 3 * 3씩 끊어서 검사
			if (! flag) {
				for (int row=0; row<9; row+=3) {
					if (flag) {
						break;
					}
					for (int col=0; col<9; col+=3) {
						if (flag) {
							break;
						}
						boolean[] v = new boolean[10];
						for (int i=0; i<3; i++) {
							if (flag) {
								break;
							}
							for (int j=0; j<3; j++) {
								int cur = myMap[row+i][col+j];
								if (v[cur]) {
									flag = true;
									break;
								} else {
									v[cur] = true;
								}
							}

							
						}
					}
				}
			}
			
			ans[tc] = (flag ? 0: 1);
		}// for
		
		for (int tc=0; tc<T; tc++) {
			System.out.println("#" + (tc+1) + " " + ans[tc]);
		}
	}
}