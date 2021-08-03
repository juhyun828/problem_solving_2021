import java.io.*;
import java.util.*;
// 210222

public class Main_BJ_2851_슈퍼마리오 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int minDiff = Integer.MAX_VALUE;
		int maxSum = 0;
		
		int[] arr = new int[10];
		int sum=0;
		for(int i=0; i<10; i++) {
			arr[i] = sc.nextInt();
			sum += arr[i];
			int diff = Math.abs(100-sum);
			if (diff<minDiff) {
				minDiff = diff;
				maxSum = sum;
			} else if(diff==minDiff) {
				maxSum = Math.max(maxSum, sum);
			}
		}
		
		System.out.println(maxSum);
		sc.close();
	}
}
