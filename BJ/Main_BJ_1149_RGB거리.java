import java.io.*;
import java.util.*;
// 210323

public class Main_BJ_1149_RGB거리 {
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = stoi(br.readLine().trim());
		
		int[][] map = new int[N+1][3];
		int[][] d = new int[N+1][3];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for(int j=0; j<3; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		// d[0][] 초기화
		d[0][0] = d[0][1] = d[0][2] = 0;
		for(int i=1; i<=N; i++) {
			// R
			d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + map[i][0];	
			// G
			d[i][1] = Math.min(d[i-1][0], d[i-1][2]) + map[i][1];
			// B
			d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + map[i][2];
		}
		
		// i==N에서 최소값 찾기
		int min = Integer.MAX_VALUE;
		for(int j=0; j<3; j++) {
			if(min>d[N][j]) min=d[N][j];
		}
		
		System.out.println(min);
		br.close();
	}
}
