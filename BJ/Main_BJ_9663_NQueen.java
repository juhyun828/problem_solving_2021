import java.io.*;
import java.util.*;

// 210301

public class Main_BJ_9663_NQueen {
	static int N, ans, cols[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		ans = 0;
		cols = new int[N+1]; // 1행부터
		
		// 1행부터 퀸 놓기 시도
		setQueen(1);
		System.out.println(ans);
		
		br.close();
	} // main
	
	static void setQueen(int r) {
		if (r >= N+1) {
			++ans;
			return;
		}
		
		// 1열부터 퀸 놓아보기
		for(int c=1; c<=N; c++) {
			cols[r] = c;
			if(isAvailable(r)) {
				setQueen(r+1);
			}
		}
	}
	
	static boolean isAvailable(int r) {
		for(int pr=1; pr<r; pr++) {
			// 같은 열에 위치한 경우
			if(cols[pr]==cols[r]) return false;
			
			// 같은 대각선 줄에 위치한 경우
			if(Math.abs(cols[pr]-cols[r])==r-pr) return false;
		}
		return true;
	}
}
