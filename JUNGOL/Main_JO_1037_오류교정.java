import java.io.*;
import java.util.*;

// 210224
public class Main_JO_1037_오류교정 {
	static int N;
	static int[][] map;
	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N+1][N+1];
		
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				map[r][c] = sc.nextInt();
			}
		}
		
		if(check()) System.out.println("OK");
		else {
			chage();
		}

		sc.close();
	}
	
	static void chage() {
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				map[r][c] = (map[r][c]==1 ? 0 : 1);
				if(check()) {
					System.out.println("Change bit (" + r + "," + c + ")");
					return;
				} else {
					map[r][c] = (map[r][c]==1 ? 0 : 1);
				}
			}
	
		}
		System.out.println("Corrupt");
		return;
	}
	
	static boolean check() {
		int sumr=0; int sumc=0;
		for(int r=1; r<=N; r++) {
			sumr=0; sumc=0;
			for(int c=1; c<=N; c++) {
				sumr+=map[r][c];
				sumc+=map[c][r];
			}
			if(sumr%2==1) return false;
			if(sumc%2==1) return false;
		}

		return true;
	}
}
