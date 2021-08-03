import java.io.*;
import java.util.*;

// 210216
// 시간초과

public class Main_BJ_2893_설탕배달_fail {
	static int N;
	static int min = Integer.MAX_VALUE;
	
	static void subset(int cnt, int three, int five, int weight) {
		
		if (weight==N) {
			System.out.println("3kg: " + three + "개, 5kg: " + five + "개, 무게: " + weight + ", cnt: " + cnt);
			min = Math.min(min, cnt);
			return;
		}
		
		if(weight>N) return;
		
		subset(cnt+1, three, five+1, weight+5);
		subset(cnt+1, three+1, five, weight+3);
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		sc.close();
		
		subset(0,  0,  0,  0);
		System.out.println((min==Integer.MAX_VALUE) ? -1 : min);
	}
}
