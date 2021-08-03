import java.io.*;
import java.util.*;
// 210225

public class Main_BJ_8320_직사각형을만드는방법 {
	static int N; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 정사각형의 개수
		int cnt = 0;
		
		for(int r=1; r<=N; r++) { // 가로줄 개수
			for(int c=r; c<=N; c++) { // 중복x
				if (r*c <= N) ++cnt;
			}
		}
		
		System.out.println(cnt);
		
		sc.close();
	}

}
