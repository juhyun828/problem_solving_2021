import java.io.*;
import java.util.*;
// 210421

public class Main_BJ_1194_달이차오른다가자 {
	static int N, M, min;
	static char[][] map;
	static int startR, startC;
	static boolean[][][] v;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Pos {
		int r, c, mask, dist;

		public Pos(int r, int c, int mask, int dist) {
			this.r = r;
			this.c = c;
			this.mask = mask;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_1194_달이차오른다가자.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new char[N][M];
		min = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if (map[i][j] == '0') {
					startR = i;
					startC = j;
					map[i][j] = '.';
				}
			}
			//System.out.println(Arrays.toString(map[i]));
		}
		
		v = new boolean[N][M][1<<6];
		bfs();
		
		System.out.println(min==Integer.MAX_VALUE ? -1 : min);

		br.close();
	}

	static void bfs() {
		Queue<Pos> q = new ArrayDeque<Pos>();
		q.offer(new Pos(startR, startC, 0, 0));
		v[startR][startC][0] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			if(map[cur.r][cur.c]=='1') {
				min = Math.min(min, cur.dist);
				return;
			}
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if(nr<0 || nr>=N || nc<0 || nc>=M || v[nr][nc][cur.mask] || map[nr][nc]=='#') continue;
				
				if(map[nr][nc]=='.' || map[nr][nc]=='1') { // 빈칸 or 출구
					v[nr][nc][cur.mask] = true;
					q.offer(new Pos(nr, nc, cur.mask, cur.dist+1));
					
				} else if('a' <= map[nr][nc] && map[nr][nc]<='f') {//열쇠
					int key = map[nr][nc] - 'a';
					int newMask = cur.mask | (1<<key);
					v[nr][nc][newMask]=true;
					q.offer(new Pos(nr, nc, newMask, cur.dist+1));
					
				} else if('A' <= map[nr][nc] && map[nr][nc]<='F') {//문
					int door = map[nr][nc] - 'A';
					if((cur.mask  & (1<<door) )> 0) {
						v[nr][nc][cur.mask]=true;
						q.offer(new Pos(nr, nc, cur.mask, cur.dist+1));
					}
				} 
			}
		}
		
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}