import java.io.*;
import java.util.*;

// 210222

public class Main_BJ_3052_나머지 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] m = new int[42]; // 42의 나머지들
		
		for(int i=0; i<10; i++) {
			int n = sc.nextInt();
			++m[n%42];
		}
		int cnt=0;
		for(int i=0; i<42; i++) {
			if(m[i]!=0) ++cnt;
		}
		
		System.out.println(cnt);
		sc.close();
	}
}
