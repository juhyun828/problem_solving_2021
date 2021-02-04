// 210204

import java.io.*;
import java.util.*;

public class Solution_D4_5432_쇠막대기자르기 {
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc=1; tc<=T; tc++) {
			char[] arr = sc.next().toCharArray();
			int cnt = 0;
			int res = 0;
			
			for (int i=0; i<arr.length; i++) {
				if (arr[i] == '(') cnt++;
				else {
					cnt--;
					if (arr[i-1] == ')') res++;
					else res += cnt;
				}
			}
			
			System.out.println("#" + tc + " " + res);
		}// for
		
		sc.close();
	}
}
