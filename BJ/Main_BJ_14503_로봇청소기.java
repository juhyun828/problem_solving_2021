import java.io.*;
import java.util.*;
// 210420

public class Main_BJ_14503_로봇청소기 {
	static int N, M, total;
	static int[][] map; // 빈 칸은 0, 벽은 1
	static int robotR, robotC, robotDir;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_14503_로봇청소기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][M];
	
		st = new StringTokenizer(br.readLine(), " ");
		robotR = stoi(st.nextToken());
		robotC = stoi(st.nextToken());
		robotDir = stoi(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}

		bfs();
		System.out.println(total);
		br.close();
	}
	
	static int turnLeft(int dir) {
		if(dir == 0) return 3;
		else if(dir==1) return 0;
		else if(dir==2) return 1;
		else if(dir==3) return 2;
		return 0;
	}
	
	static int turnBack(int dir) {
		if(dir == 0) return 2;
		else if(dir==1) return 3;
		else if(dir==2) return 0;
		else if(dir==3) return 1;
		return 0;
	}
	
	static class Pos {
		int r, c, dir;

		public Pos(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	
	static void bfs() {
		int[][] v = new int[N][M];
		Queue<Pos> q=  new ArrayDeque<Pos>();
		
		// 로봇 시작 위치
		total = 1; 
		v[robotR][robotC] = total;
		q.offer(new Pos(robotR, robotC, robotDir));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();

			int nd = cur.dir;
			boolean doClean = false;
			for(int d=0; d<4; d++) {
				nd = turnLeft(nd);
				int nr = cur.r + dr[nd];
				int nc = cur.c + dc[nd];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc]==0 && v[nr][nc]==0) {
					// 청소
					doClean = true;
					++total;
					v[nr][nc] = total;
					q.offer(new Pos(nr, nc, nd));
					break;
				}
			}

			// 네방향 모두 청소되있거나 벽인 경우
			if(!doClean) {
				// 바라보는 방향을 유지한 채로 한칸 후진
				nd = turnBack(cur.dir);
				int nr = cur.r + dr[nd];
				int nc = cur.c + dc[nd];

				if(nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc]==0 && v[nr][nc]==0) {
					// 청소
					++total;
					v[nr][nc] = total;
					q.offer(new Pos(nr, nc, cur.dir)); // 바라보는 방향은 유지해야 함
				} else if(nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc]==0) {
					// 후진은, 이미 청소한 칸은 갈 수 있음
					q.offer(new Pos(nr, nc, cur.dir)); 
				} else { 	// 후진도 못하면 작동을 멈춘다.
					return;
				}
			}
			
		} // while

	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
