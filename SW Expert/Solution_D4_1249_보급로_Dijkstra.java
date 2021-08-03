import java.io.*;
import java.util.*;
// 210412

public class Solution_D4_1249_보급로_Dijkstra {
	static int N;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_D4_1249_보급로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = stoi(br.readLine());
			map = new int[N][N];
		
			for(int i=0; i<N; i++) {
				char[] c = br.readLine().toCharArray();
				for(int j=0; j<N; j++) {
					map[i][j] = c[j]-'0';
				}
			}
			
			System.out.println("#" + tc + " " + dijkstra(0, 0));
		}
	
		br.close();
	}
		
	static int dijkstra(int startX, int startY) {
		boolean[][] v = new boolean[N][N];
		int[][] minTime = new int[N][N];
		
		// 모든 위치 최대값으로 초기화
		for(int i=0; i<N; i++)
			Arrays.fill(minTime[i], INF);
		
		PriorityQueue<Pos> pq = new PriorityQueue<Pos>(new Comparator<Pos>() {
			@Override
			public int compare(Pos o1, Pos o2) {
				return Integer.compare(o1.total, o2.total);
			}
		});
		
		// 시작점
		minTime[startX][startY] = 0;
		pq.offer(new Pos(startX, startY, minTime[startX][startY]));
		
		int nr, nc;
		Pos cur;
		while(true) {
			cur = pq.poll();
			
			if(v[cur.r][cur.c]) continue;
			v[cur.r][cur.c] = true;
			if(cur.r==N-1 && cur.c==N-1) return cur.total;
			
			for(int d=0; d<4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
				if(!v[nr][nc] && minTime[nr][nc]>cur.total+map[nr][nc]) {
					minTime[nr][nc] = cur.total+map[nr][nc];
					pq.offer(new Pos(nr, nc, minTime[nr][nc]));
				}
			}
		}
	}
	
	static class Pos{
		int r, c, total;

		public Pos(int r, int c, int total) {
			this.r = r;
			this.c = c;
			this.total = total;
		}
	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
