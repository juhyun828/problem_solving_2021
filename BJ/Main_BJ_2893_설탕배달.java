import java.io.*;
import java.util.*;

// 210216

public class Main_BJ_2893_설탕배달 {
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		sc.close();
		
		int weight = N;
		int cnt= 0;
		// 18 - 3 
		while (weight>2) {
			if(weight%5==0) {
				cnt += weight/5;
				weight %= 5;
				break;
			} else {
				++cnt;
				weight-=3;
			}
		}
		System.out.println(weight==0 ? cnt : -1);
		
	}
}
