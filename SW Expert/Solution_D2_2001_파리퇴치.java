import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D2_2001_파리퇴치 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = 0;
			int sum;
			for(int r=0; r<=N-M; r++) {
				for(int c=0; c<=N-M; c++) {
					sum = 0;
					for (int mi=0; mi<M; mi++) {
						for (int mj=0; mj<M; mj++) {
							sum += map[r+mi][c+mj];
						}
					}
					if (max<sum) max = sum;
				}
			}
			System.out.println("#" + tc + " "+ max);

		}
		br.close();
	} // main

}
