import java.io.*;
import java.util.*;
// 210418

public class Main_BJ_2239_스도쿠 {
	static int N=9, size;
	static int[][] board;
	static List<Pos> list;
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_2239_스도쿠.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		board = new int[10][10];
		list = new ArrayList<Pos>();
		
		char[] ch;
		for(int i=1; i<=9; i++) {
			ch = br.readLine().toCharArray();
			for(int j=1; j<=9; j++) {
				board[i][j] = ch[j-1]-'0';
				if(board[i][j]==0) list.add(new Pos(i, j));
			}
		}
		
		size = list.size();
		
		recursive(0); // 리스트 첫번째 빈칸부터 숫자 채우고 시도

		br.close();
		
	}
	
	static void recursive(int idx) {

		if(idx==size) {
			printBoard();
			System.exit(0);
		}
		
		Pos cur = list.get(idx);
		for(int num=1; num<=9; num++) {
			// 1부터 9까지 대압하기
			if(checkBoard(cur.r, cur.c, num)) { // 대입 가능하면 숫자라면
				board[cur.r][cur.c] = num;
				recursive(idx+1); // 그 다음 빈칸
				board[cur.r][cur.c] = 0;
			}
		}
		
	}

	static boolean checkBoard(int r, int c, int num) {
		// 행 열 중복검사
		for(int i=1; i<=9; i++) {
			if(board[r][i]==num) return false;
			if(board[i][c]==num) return false;
		}
		
		// 3 * 3 부분 격자열 중복 검사
		
		// 시작 행 결정
		int sr = 0;
		if(r%3==1) sr = r;
		else if(r%3==2) sr = r-1;
		else if(r%3==0) sr = r-2;
		
		// 시작 열 결정
		int sc = 0;
		if(c%3==1) sc = c;
		else if(c%3==2) sc = c-1;
		else if(c%3==0) sc = c-2;
		
		for(int i=sr; i<sr+3; i++) {
			for(int j=sc; j<sc+3; j++) {
				if(board[i][j]==num) return false;
			}
		}
		
		return true;
	}

	static void printBoard() {
		for(int i=1; i<=9; i++) {
			for(int j=1; j<=9; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

}
