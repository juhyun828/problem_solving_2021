import java.io.*;
import java.util.*;

// 210214

public class Main_BJ_7562_나이트의이동 {
	static int I;
	static int[][] map;
	static int[][] dist;
	static Queue<Integer[]> q;
	static int[] dr = {-2, -2, -1, -1,  1, 1, 2 , 2 };
	static int[] dc = {-1,  1, -2,  2, -2, 2, -1, 1 };
	
	static void bfs(int curRow, int curCol, int destRow, int destCol) {
		q.offer(new Integer[] {curRow, curCol});
		dist[curRow][curCol] = 0;
		
		while(!q.isEmpty()) {
			Integer[] v = q.poll();
			int r = v[0], c = v[1];
			for(int d=0; d<8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr>=0 && nr<I && nc>=0 && nc<I && dist[nr][nc]==0) {
					dist[nr][nc] = dist[r][c] + 1;
					q.offer(new Integer[] {nr, nc});
					if (nr==destRow && nc==destCol) return;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			I = Integer.parseInt(br.readLine());
			map = new int[I][I];
			dist = new int[I][I];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int curRow = Integer.parseInt(st.nextToken()); 
			int curCol = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			int destRow = Integer.parseInt(st.nextToken()); 
			int destCol = Integer.parseInt(st.nextToken());
			
			q = new ArrayDeque<Integer[]>();
			if(curRow==destRow && curCol==destCol) {
				System.out.println("0");
			} else {	
				bfs(curRow, curCol, destRow, destCol);
				System.out.println(dist[destRow][destCol]);				
			}
		}
		
		br.close();
	}
}
