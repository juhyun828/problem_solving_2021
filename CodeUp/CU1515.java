import java.util.Scanner;

// 210125
// 1515 : 생명 게임 1

public class CU1515 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[][] myMap = new int[25][25];
		
		int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
		int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
		
		for (int i=0; i<25; i++) {
			for (int j=0; j<25; j++) {
				myMap[i][j] = sc.nextInt();
			}
		}
		
		int[][] yourMap = new int[25][25];
		
		for (int i=0; i<25; i++) {
			for (int j=0; j<25; j++) {
				// 생명 x
				if (myMap[i][j] == 0) {
					int cnt = 0;
					
					for (int z=0; z<8; z++) {
						if (cnt > 3) {
							yourMap[i][j] = 0;
							break;
						}
						int ni = i+dx[z];
						int nj = j+dy[z];
						if (ni<0 || ni>=25 || nj<0 || nj>=25) {
							continue;
						} else {
							if (myMap[ni][nj] == 1) cnt++;
						}
					} 					
					if (cnt == 3) yourMap[i][j] = 1; 
					else yourMap[i][j] = 0; 
					
				} // if
				// 생명 x
				else if (myMap[i][j] == 1) {
					int cnt = 0;

					for (int z=0; z<8; z++) {
						int ni = i+dx[z];
						int nj = j+dy[z];
						if (ni<0 || ni>=25 || nj<0 || nj>=25) {
							continue;
						} else {
							if (myMap[ni][nj] == 1) cnt++;
						}
					} 					
					if ((cnt==2) || (cnt==3)) yourMap[i][j] = 1;
					else yourMap[i][j] = 0;
				}
				
			}
		}
				
		// 출력
		for (int i=0; i<25; i++) {
			for (int j=0; j<25; j++) {
				System.out.print(yourMap[i][j] + " ");
			}
			System.out.println();
		}
		
		sc.close();
	}
}