import java.io.*;
import java.util.*;
// 210217

public class Main_BJ_1992_쿼드트리 {
	static int N;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static boolean check(int len, int sr, int sc) {
		if (len==1) return true;
		
		for(int r=sr; r<sr+len-1; r++) {
			for(int c=sc; c<sc+len-1; c++) {
				if( (map[r][c]==map[r][c+1]) && (map[r][c+1]==map[r+1][c]) && (map[r+1][c]==map[r+1][c+1]))
					continue;
				else return false;
			}
		}
		return true;
	}
	
	static void divide(int len, int sr, int sc) {
		if (check(len, sr, sc)) {
			sb.append(map[sr][sc]);
			return;
		}
		
		int halfLen = len/2;
		// 1사분면
		sb.append("(");
		divide(halfLen, sr, sc);
		// 2사분면
		divide(halfLen, sr, sc+halfLen);
		// 3사분면
		divide(halfLen, sr+halfLen, sc);
		// 4사분면
		divide(halfLen, sr+halfLen, sc+halfLen);
		sb.append(")");
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_1992_쿼드트리.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = stoi(br.readLine());
		map = new int[N][N];
		String[] str = null;
		for(int r=0; r<N; r++) {
			str = br.readLine().trim().split("");
			for(int c=0; c<N; c++) {
				map[r][c] = stoi(str[c]);
			}
		}
		 divide(N, 0, 0);
		 System.out.println(sb.toString());

		br.close();
	}

}
