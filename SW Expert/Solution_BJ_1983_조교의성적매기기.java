// 210201

import java.io.*;
import java.util.*;

public class Solution_BJ_1983_조교의성적매기기 {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		
		String[] grade = {"", "D0", "C-", "C0", "C+", "B-","B0", "B+", "A-", "A0", "A+"};
		
		int T = sc.nextInt();
		
		for (int tc=1; tc<=T; tc++) {
			int N = sc.nextInt(); // 학생 수
			int K = sc.nextInt(); // 성적을 알고 싶은 학생 번호
			float[] score = new float[N+1];
			float KS = 0;
			
			for (int si=1; si<=N; si++) {
				float s = (float) (sc.nextInt()*35 + sc.nextInt()*45 + sc.nextInt()*20)/100;
				score[si] = s;
				if (si==K) KS = s;
			}

			Arrays.sort(score);
			
			int ratio = N/10;
			String[] res = new String[N+1];
			
			int num = 1;
			for (int i=1; i<=10; i++) {
				for (int j=1; j<=ratio; j++) {
					res[num++] = grade[i];
				}
			}
			
			for (int i=1; i<=N; i++) {
				if (score[i] == KS) {
					System.out.println("#" + tc + " " + res[i]);
					break;					
				}
			}
		}

	}

}