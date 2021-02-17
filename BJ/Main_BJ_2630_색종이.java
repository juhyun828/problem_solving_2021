import java.io.*;
import java.util.*;
// 210217

public class Main_BJ_2630_색종이 {
	static int N;
	static int[][] map;
	static int blCnt=0;
	static int whCnt=0;
	
	static void print(int len, int sr, int sc) {
		for(int r=sr; r<len; r++) {
			for(int c=sc; c<len; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
	
	static int stoi (String str) {
		return Integer.parseInt(str);
	}
	
	static void divide(int len, int sr, int sc) {
		// 다 같은 배열인지 체크
		boolean flag = check(len, sr, sc);
		if (flag) {
			int color = map[sr][sc];
			if(color==1) ++blCnt;
			else ++whCnt;
			return;
		}
		
		// 같은 배열x -> 분할
		int halfLen = len/2;
		
		divide(len/2, sr, sc);
		divide(len/2, sr, sc+halfLen);
		divide(len/2, sr+halfLen, sc);
		divide(len/2, sr+halfLen, sc+halfLen);
		
	}
	
	static boolean check(int len, int sr, int sc) {
		if (len==1) {
			return true;
		}
		for(int r=sr; r<sr+len-1; r++) {
			for(int c=sc; c<sc+len-1; c++) {
				if ((map[r][c]==map[r][c+1]) && (map[r][c+1]==map[r+1][c]) && (map[r+1][c]==map[r+1][c+1])) {
					continue;
				} else {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_2630_색종이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		map = new int[N][N];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<N; c++) {
				map[r][c] = stoi(st.nextToken());
			}
		}
		
		divide(N, 0, 0);
		System.out.println(whCnt + "\n" + blCnt);
		br.read();
	}
}
