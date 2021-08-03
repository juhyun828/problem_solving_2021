// 210223

import java.io.*;
import java.util.*;

public class Main_BJ_10163_색종이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] map = new int[101][101];
		int[] cnt = new int[N+1];
		
		for(int k=1; k<=N; k++) {
			int c=sc.nextInt(); int r=sc.nextInt();
			int w=sc.nextInt(); int h=sc.nextInt();
			
			for(int i=0; i<w; i++) {
				for(int j=0; j<h; j++) {
					int nc = c + i; int nr = 100-(r+j);
					map[nr][nc] = k;
				}
			}
		}
		
		for(int i=0; i<101; i++) {
			for(int j=0; j<101; j++) {
				++cnt[map[i][j]];
			}
		}
		
		// print
		for(int i=1; i<=N; i++) {
			System.out.println(cnt[i]);
		}
		
		sc.close();
	}

}
