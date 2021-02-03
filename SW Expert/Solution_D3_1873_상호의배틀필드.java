// 210203

import java.io.*;
import java.util.*;

public class Solution_D3_1873_상호의배틀필드 {
	
	static int H;
	static int W;
	static char[][] map;
	static char[] input;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[] dArrow = {'^', 'v', '<', '>'};
	
	static int d;
	static int goRow;
	static int goCol;

	private static void printArr() {
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
	} //
	
	private static void shoot(int goRow, int goCol, int d) {
		int nr = goRow + dr[d];
		int nc = goCol + dc[d];
		
		if (nr<0 || nr>=H || nc<0 || nc>=W) {
			// 지도 밖으로 
			return;

		} else if (map[nr][nc] == '#' ) {
			// 강철 벽 
			return;
		} else if (map[nr][nc] == '-') {
			// 물 
			shoot(nr, nc, d);
			
		} if (map[nr][nc] == '*') {
			// 벽돌 벽은 부수고 
			map[nr][nc] = '.';
			return;

		} else if (map[nr][nc] == '.') {
			shoot(nr, nc, d);
		}
	}
	
	private static void go(int nr, int nc) {
		if (nr<0 || nr>=H || nc<0 || nc>=W) {
			// 지도 밖으로 
			return;
		} else if (map[nr][nc] == '.') {
			// 이동
			map[goRow][goCol] = '.';
			goRow = nr;
			goCol = nc;
			map[goRow][goCol] = dArrow[d];
			return;
		}
		return;
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc=1; tc<=T; tc++) {
			H = sc.nextInt();
			W = sc.nextInt();
			
			map = new char[H][W];
			
			// 입력
			for (int r=0; r<H; r++) {
				char[] chArr = sc.next().toCharArray();
				for (int c=0; c<W; c++) {
					char a = chArr[c];
					map[r][c] = a;
					if (a == '^') {
						d = 0;
						goRow = r;
						goCol = c;
					} else if (a == 'v') {
						d = 1;
						goRow = r;
						goCol = c;
					} else if (a == '<') {
						d = 2;
						goRow = r;
						goCol = c;
					} else if (a == '>') {
						d = 3;
						goRow = r;
						goCol = c;
					}
				}
			} // for 
			
			int N = sc.nextInt();
			input = sc.next().toCharArray();
			
			for (int i=0; i<N; i++) {
				
				if (input[i] == 'S') {
					map[goRow][goCol] = dArrow[d];
					shoot(goRow, goCol, d);
					
				} else if (input[i] == 'U') {
					d = 0;
					map[goRow][goCol] = dArrow[d];
					go(goRow + dr[d], goCol + dc[d]);
					
				} else if (input[i] == 'D') {
					d = 1;
					map[goRow][goCol] = dArrow[d];
					go(goRow + dr[d], goCol + dc[d]);
					
				} else if (input[i] == 'L') {
					d = 2;
					map[goRow][goCol] = dArrow[d];
					go(goRow + dr[d], goCol + dc[d]);
					
				} else if (input[i] == 'R') {
					d = 3;
					map[goRow][goCol] = dArrow[d];
					go(goRow + dr[d], goCol + dc[d]);
				} 
			} // for
			System.out.print("#" + tc + " ");
			printArr();
			
		} // T
		
		sc.close();
		
	} // main

}
