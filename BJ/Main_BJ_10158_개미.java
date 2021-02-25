import java.io.*;
import java.util.*;
// 210225
// https://www.acmicpc.net/problem/10158

public class Main_BJ_10158_개미 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int h = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int t = sc.nextInt(); // 초
		
		// 우로 w초, 좌로 w초, 총 2w초 후에 개미의 x 위치는 초기 위치와 같다. 
		// => 따라서 2w초 가고난 나머지 시간에 이동한 위치만 계산해준다.
		int tx = t % (2*w);
		// 위로 h초, 아래로 h초, 총 2h초 후에 개미의 y 위치는 초기 위치와 같다. 
		int ty = t % (2*h);
		
		int d=1;
		for(int i=0; i<tx; i++) {
			if (x==w || x==0) d*=-1; // 위나 바닥에 닿으면 방향 전환
			x += d;
		}
		d=1;
		for(int i=0; i<ty; i++) {
			if (y==h || y==0) d*=-1; // 위나 바닥에 닿으면 방향 전환
			y += d;
		}
		
		System.out.println(x + " " + y);
		
		sc.close();
	} // main
}
