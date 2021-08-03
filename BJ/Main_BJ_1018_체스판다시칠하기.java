import java.io.*;
import java.util.*;
// 210211

public class Main_BJ_1018_체스판다시칠하기 {
	static int N, M;
	static char[][] map;
	static int min;
	static char[][] chess1 = {
			{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
			{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
			{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
			{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
			{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
			{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
			{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
			{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'}
			
	};
	
	static char[][] chess2 = {
			{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
			{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
			{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
			{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
			{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
			{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
			{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
			{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'}
			
	};
	
	static void printArr(int sr, int sc) {
		for(int r=sr; r<sr+8; r++) {
			for(int c=sc; c<sc+8; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
	
	
	static void chess(int sr, int sc) {
		
//		printArr(sr, sc);
		
		int cnt1 = 0;
		int cnt2 = 0;
		int chessR = 0;
		int chessC = 0;
		
		for(int r=sr; r<sr+8; r++, chessR++) {
			chessC = 0;
			for (int c=sc; c<sc+8; c++, chessC++) {
				if (map[r][c] != chess1[chessR][chessC]) cnt1++;
				if (map[r][c] != chess2[chessR][chessC]) cnt2++;
			}
		}// for
		
		// cnt 크기 비교하기
		min = Math.min(min, Math.min(cnt1, cnt2));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		
		map = new char[N][M];
		
		for(int r=0; r<N; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		// 8 * 8로 나누기
		for(int r=0; r<=N-8; r++) {
			for(int c=0; c<=M-8; c++) {
				chess(r, c);
			}
		}
		
		System.out.println(min);
		br.close();
	} // main

}
