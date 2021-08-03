// 210222

import java.io.*;
import java.util.*;

public class Main_BJ_13300_방배정 {
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] stu = new int[2][7]; // 학년, 성별
		
		int cnt=0;
		for(int i=0; i<N; i++) {
			int gender = sc.nextInt();
			int grade = sc.nextInt();
			if (stu[gender][grade]==0) ++cnt;
			stu[gender][grade]++;
			if (stu[gender][grade]>K) {
				++cnt;
				stu[gender][grade] = 1;
			}
		}
		System.out.println(cnt);
		
		sc.close();
	}
}
