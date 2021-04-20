import java.io.*;
import java.util.*;
// 210420

public class Main_BJ_2667_단지번호붙이기 {
	static int N, cnt_dfs;
	static int[][] map, v;
	static Queue<Pos> q;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static ArrayList<Integer> res;
	
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_2667_단지번호붙이기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = stoi(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0; j<N; j++) {
				map[i][j] = ch[j]-'0';
			}
		}
		
		v = new int[N][N];
		q = new ArrayDeque<Pos>();
		res = new ArrayList<Integer>();
		
		// 시작점
		int idx = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1 && v[i][j]==0) {
					//res.add(bfs(i, j, ++idx));
					cnt_dfs = 1;
					dfs(i, j, ++idx);
					res.add(cnt_dfs);
				}
			}
		}
		
		Collections.sort(res);
		StringBuilder sb = new StringBuilder();
		sb.append(res.size()+ "\n");
		for(int i: res) {
			sb.append(i + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static int bfs(int r, int c, int idx) {
		
		v[r][c]=idx;
		q.offer(new Pos(r, c));
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N || map[nr][nc]==0 || v[nr][nc]!=0) continue;
				++cnt;
				v[nr][nc] = idx;
				q.offer(new Pos(nr, nc));
			}
		}
		return cnt;
	}
	
	static void dfs(int r, int c, int idx) {
		v[r][c]=idx;

		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N || map[nr][nc]==0 || v[nr][nc]!=0) continue;
			v[nr][nc] = idx;
			++cnt_dfs;
			dfs(nr, nc, idx);
		}
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
