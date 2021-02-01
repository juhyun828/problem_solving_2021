// 210201

import java.io.*;
import java.util.*;

public class Main_BJ_1244_스위치켜고끄기  {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int SN = sc.nextInt();
		int[] arr = new int[SN+1];
		
		for(int i=1; i<SN+1; i++) {
			arr[i] = sc.nextInt();
		}
		
		int N = sc.nextInt(); // 학생 수
		
		for (int si=1; si<=N; si++) {
			int g = sc.nextInt(); // 성별
			int num = sc.nextInt(); // 스위치 번호
			
			if (g == 1) {
				// 남학생
				for (int i=num; i<=(arr.length-1); i+=num) {
					arr[i] = (arr[i]==1) ? 0 : 1;
				}
			} else if (g == 2) {
				// 여학생
				arr[num] = (arr[num]==1) ? 0 : 1;
				for (int i=1; i<(arr.length-1); i++) {
					int l = num - i;
					int r = num + i;
					
					if (l<1 || r > (arr.length-1) || arr[l]!=arr[r]) 
						break;
					else {
						arr[l] = (arr[l]==1) ? 0 : 1;
						arr[r] = (arr[r]==1) ? 0 : 1;
					}
				} 
				
			}
				
		}
			
		// 출력
		for (int i=1; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
			if (i%20 == 0) System.out.println();
		}
		
		sc.close();
	}

}
