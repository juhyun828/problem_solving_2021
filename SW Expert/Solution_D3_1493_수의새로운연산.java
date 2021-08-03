import java.io.*;
import java.util.*;

// 210214

public class Solution_D3_1493_수의새로운연산 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/inputD3_1493_수의새로운연산.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			if (p>q) {
				int tmp = p;
				p = q;
				q = tmp;
			}
			
			int[][] map = new int[301][301]; // 왜 300,,?
			
			int[] pPos = new int[2];
			int[] qPos = new int[2]; 
			
			int num = 1;

			for(int sr=1; sr<=300; sr++) {
				for(int i=1; i<=sr; i++) {
					int nr = sr - (i-1);
					int nc = i;
					map[nr][nc] = num;
					num++;
					if (p==q && map[nr][nc]==p) {
						pPos[0] = nr;
						pPos[1] = nc;
						qPos[0] = nr;
						qPos[1] = nc;
					}
					
					if (map[nr][nc]==p) {
						pPos[0] = nr;
						pPos[1] = nc;
						
					} else if (map[nr][nc]==q) {
						qPos[0] = nr;
						qPos[1] = nc;
					}
				}
				//}
				
				
			} // while
			System.out.println("#" + tc + " " + map[pPos[0] + qPos[0]] [pPos[1] + qPos[1]]);
		}
		
		br.close();
	}

}
