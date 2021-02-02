package day0202;

// // 210102
import java.io.*;
import java.util.*;

public class Solution_D3_1208_Flatten {

	public static void main(String[] args) throws Exception {
		System.setIn(
				new FileInputStream("res/input_D3_1208_Flatten.txt"));
		Scanner sc = new Scanner(System.in);
		
		for (int tc=1; tc<=10; tc++) {
			
			int dump = sc.nextInt();
			int[] arr = new int[100];
			
			for (int i=0; i<100; i++) {
				arr[i] = sc.nextInt();
			}
			
			Arrays.sort(arr);
			for (int i=1; i<=dump; i++) {
				if (arr[99]-arr[0] <= 1) // 전체가 평탄
					break;
				arr[99] -= 1;
				arr[0] +=1;
				Arrays.sort(arr);
			} 
			
			System.out.println("#" + tc + " " + (arr[99]-arr[0]));
		}

	}

}
