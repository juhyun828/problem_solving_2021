import java.util.Scanner;

// 210128
// 1204. [S/W 문제해결 기본] 1일차 - 최빈수 구하기

public class Solution_D2_1204_최빈수구하기 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		
		int T = sc.nextInt();
		for(int tc=0; tc<T; tc++) {
			int TN = sc.nextInt();
			
			int[] arr = new int[101];
			int maxScore = -1;
			int maxCnt = -1;
			for (int stu=0; stu<1000; stu++) {
				int score = sc.nextInt();
				arr[score] += 1;
				if (arr[score] > maxCnt) {
					maxCnt = arr[score];
					maxScore = score;
				} else if (arr[score] == maxCnt) {
					// 최빈수가 여러 개 일 때에는 가장 큰 점수
					if (score >= maxScore) {
						maxScore = score;
						maxCnt = arr[score];
					}
				}
			} // for
			System.out.println("#" + TN + " " + maxScore);
			
		}
		
		sc.close();
	} // main

}