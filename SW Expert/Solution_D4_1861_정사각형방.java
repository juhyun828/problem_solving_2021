import java.io.*;
import java.util.*;

// 210205 

public class Solution_D4_1861_정사각형방 {
	
	static int[][] map;
	static int N;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1}; // 상 하 좌 우
	
	static int maxCnt;
	static int minRoomNum;
	
	// 처음 출발해야 하는 방 번호 , 최대 몇개 이동 출력
	
	static void check(int r, int c, int cnt, int start) {
		for (int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr>=0 && nr<N && nc>=0 && nc<N ) {
				if (map[nr][nc] == (map[r][c]+1))
					check(nr, nc, cnt+1, start);
			}
			
		}
		
		if (cnt > maxCnt) {
			maxCnt = cnt;
			minRoomNum = start;
		}
		if (cnt == maxCnt) {
			minRoomNum = Math.min(minRoomNum, start);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for (int tc=1; tc<=T; tc++) {

			N = sc.nextInt();
			
			map = new int[N][N];
			maxCnt = 0;
			minRoomNum = 0;
			
			for(int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					map[r][c] = sc.nextInt();
				}
			}
			
			for(int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					check(r, c, 1, map[r][c]);
				}
			}
			
			System.out.println("#" + tc + " " + minRoomNum + " " + maxCnt);
			
		}
		
		sc.close();
	}

}
