import java.io.*;
import java.util.*;

//210210 

public class Main_BJ_16926_배열돌리기1 {
	
	static int N, M, R;
	static int[][] arr;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static StringBuilder sb = new StringBuilder();
	
	static void printArr() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				sb.append(arr[r][c] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void rotate() {
		for(int s=0; s<Math.min(N, M)/2; s++) {
			int sr = s;
			int sc = s;
			int startValue = arr[sr][sc];
			
			int d = 0;
			
			while(d < 4) {
				int nr = sr + dr[d];
				int nc = sc + dc[d];
				
				// 범위 안에 있으면
				if (nr>=s && nr<(N-s) && nc>=s && nc<(M-s)) {
					arr[sr][sc] = arr[nr][nc];
					sr = nr;
					sc = nc;
				} else {
					d++;
				}
			}
			arr[s+1][s] = startValue;
		}
	}
 	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				
			}
		}
		
		for(int ri=0; ri<R; ri++) {
			rotate();
		}
		
		printArr();
		
		br.close();
	}

}
