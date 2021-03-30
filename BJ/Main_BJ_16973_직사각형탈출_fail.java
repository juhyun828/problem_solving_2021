import java.io.*;
import java.util.*;
// 210330

public class Main_BJ_16973_직사각형탈출_fail {
	static int N, M, H, W;
	static int[][] map, v;
	static int sr, sc, er, ec;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_BJ_16973_직사각형탈출.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		map = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		} 
		
		st = new StringTokenizer(br.readLine(), " ");
		
		H = stoi(st.nextToken());
		W = stoi(st.nextToken());
		
		sr = stoi(st.nextToken());
		sc = stoi(st.nextToken());
		er = stoi(st.nextToken());
		ec = stoi(st.nextToken());
	
		bfs(new Pos(sr, sc));
		
		if(v[er][ec]==0)
			System.out.println(-1);
		else
			System.out.println(v[er][ec]);
		
		br.close();
	}
	
	static void bfs(Pos start) {
		Queue<Pos> q = new ArrayDeque<>();
		v = new int[N+1][M+1]; 
		
		// 시작 지점 넣어주기
		q.offer(start);
		
		Pos cur;
		int nr, nc, nnr, nnc;
		boolean flag = true;
		while(!q.isEmpty()) {
			cur = q.poll();
            if(cur.r == er && cur.c==ec)
				return;
			
			for(int d=0; d<4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				if(nr==start.r && nc==start.c) continue;
				if(nr<1 || nr>N || nc<1 || nc>M || v[nr][nc]!=0 || map[nr][nc]==1) continue;
				flag = true;
				for(int hi=0; hi<H; hi++) {
					for(int wi=0; wi<W; wi++) {
						nnr = nr + hi;
						nnc = nc + wi;
						if(nnr<1 || nnr>N || nnc<1 || nnc>M || map[nnr][nnc]==1) {
							flag = false;
							break;
						}
					}
				}// 
				
				if(flag) {
					// 이동 가능
					v[nr][nc] = v[cur.r][cur.c] + 1;
					q.offer(new Pos(nr, nc));
				}
			}
			
		}
		
	}
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}