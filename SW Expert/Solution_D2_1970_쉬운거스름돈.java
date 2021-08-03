// 210102
import java.io.*;
import java.util.*;

public class Solution_D2_1970_쉬운거스름돈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] won = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
		
		int T = sc.nextInt();
		
		for (int tc=1; tc<=T; tc++) {
			int cost = sc.nextInt();
			int[] res = new int[8];
			
			for (int i=0; i<8; i++) {
				int w = won[i];
				while((cost-w)>=0) {
					cost -= w;
					res[i]++;
				}
			}
			
			System.out.println("#" + tc);
			for (int i=0; i<8; i++) {
				System.out.print(res[i] + " ");
			}
			System.out.println();
		}

		sc.close();
	} // main

}
