import java.io.*;
import java.util.*;
// 210331

public class Main_BJ_16973_직사각형탈출 {
	static int N, M, H, W;
	static int[][] map;
	static int sr, sc, er, ec;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0,  0, -1, 1}; // 상 하 좌 우
	
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
	
		System.out.println(bfs(new Pos(sr, sc, 0)));
		
		br.close();
	}
	
	static int bfs(Pos start) {
		// 시작점 방문
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] v = new boolean[N+1][M+1];
		
		q.offer(start);
		v[start.r][start.c] = true;
		
		Pos cur;
		int nr, nc, nnr, nnc;
		boolean flag = true;
		
		while(!q.isEmpty()) {
			cur = q.poll();
			
			if(cur.r==er && cur.c==ec)
				return cur.dist;
			
			// 4방 탐색
			for(int d=0; d<4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				
				// 정사각형의 네 꼭지점 범위 탐색 
				if(nr<1 || nr>N || nc<1 || nc>M || v[nr][nc] || 
						nr+H-1>N || nc+W-1>M ) continue;
				
				// 이동할 위치에 벽이 있는지 검사
				flag = true;
				if(d==0) { // 상
					nnr = nr;
					for(int i=0; i<W; i++) {
						nnc = nc + i;
						if(map[nnr][nnc]==1) {
							flag=false;
							break;
						}
					}
					
				} else if(d==1) { // 하
					nnr = nr +H-1;
					for(int i=0; i<W; i++) {
						nnc = nc + i;
						if(map[nnr][nnc]==1) {
							flag=false;
							break;
						}
					}

				} else if(d==2) { // 좌
					nnc = nc;
					for(int i=0; i<H; i++) {
						nnr = nr + i;
						if(map[nnr][nnc]==1) {
							flag=false;
							break;
						}
					}
					
				} else if(d==3) { // 우
					nnc = nc+W-1;
					for(int i=0; i<H; i++) {
						nnr = nr + i;
						if(map[nnr][nnc]==1) {
							flag=false;
							break;
						}
					}
					
				}
				
				if(flag) {
					q.offer(new Pos(nr, nc, cur.dist+1));
					v[nr][nc] = true;
				}

			} // for

		}

		return -1;
	}

	static class Pos {
		int r, c, dist;

		public Pos(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
