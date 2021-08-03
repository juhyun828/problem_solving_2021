import java.io.*;
import java.util.*;

// 210224
public class Main_JO_1037_오류교정_2 {
	static int N;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N+1][N+1];
		int[] rows = new int[N+1];
		int[] cols = new int[N+1];
		
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				map[r][c] = sc.nextInt();
				if(map[r][c]==1) {
					++rows[r];
					++cols[c];
				}
			}
		}
		
		int ri=-1, ci=-1;
		for(int i=1; i<=N; i++) {
			if(rows[i]%2==1) {
				if (ri!=-1) {
					System.out.println("Corrupt");
					return;
				}
				else {
					ri=i;
				}
			}
			if(cols[i]%2==1) {
				if (ci!=-1) {
					System.out.println("Corrupt");
					return;
				}
				else {
					ci=i;
				}
			}
		}
		if(ri==-1 && ci==-1) System.out.println("OK");
		else System.out.println("Change bit (" + ri + "," + ci + ")");


		sc.close();
	}

}
