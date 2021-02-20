import java.io.*;
import java.util.*;

// 210220

public class Main_BJ_9663_NQueen {
	static int N;
	static int[] col;
	static int cnt=0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new int[N+1]; // 1열부터
		
		setQueen(1);
		System.out.println(cnt);
	} // main
	
	static void setQueen(int r) {
		if (r>N) {
			++cnt;
			return;
		} 
		// 해당 행의 1열부터 놓는다.
		// 1열부터 놓을 수 있는지 체크하고. 놓을 수 있다면 다음 행
		for(int c=1; c<=N; c++) {
			col[r] = c;
			if (check(r, c)) {
				setQueen(r+1); // 해당 열에 둘 수 있으면 다음 행 시도
			}
		}
		
	}
	
	// 해당 행 이전까지, 같은 위치나 대각선에 있었는지 확인한다.
	static boolean check(int r, int c) {
		for(int k=1; k<r; k++) {
			if (col[r]==col[k] || Math.abs(col[r]-col[k])==(r-k)) {
				return false;
			}
		} 
		return true;
	}
}
