// 210204

import java.io.*;
import java.util.*;

public class Solution_D4_1210_Ladder1  {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc=1; tc<=10; tc++) {
			int tn = Integer.parseInt(br.readLine());
			
			StringTokenizer st = null;
			int goRow = 0;
			int goCol = 0;
			int[][] map = new int[102][102];
			int[] dr = {0, 0, -1};
			int[] dc = {-1, 1, 0};
			
			// 지도 입력
			for (int r=1; r<=100; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c=1; c<=100; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 2) {
						goRow = r;
						goCol = c;
					}
				}
			}
			
			while (true) {
				if (goRow == 1) break;
				for (int d=0; d<3; d++) {
					int nRow = goRow + dr[d];
					int nCol = goCol + dc[d];
					
					if (map[nRow][nCol] == 1) {
						
						map[goRow][goCol] = 0; 
						goRow = nRow;
						goCol = nCol;
						break;
					}
				}
			}
			
			System.out.println("#" + tc + " " + (goCol-1));
			
		}

		br.close();
	} // main

}
