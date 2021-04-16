import java.io.*;
import java.util.*;
// 210416

public class Main_BJ_17780_새로운게임 {
	static int N, K; // 체스판 크기, 말 개수
	static int[][] color; // 0: 흰색, 1: 빨강, 2: 파랑
	static Queue<Integer>[][] qmap;
	static Player[] players;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {1, -1, 0, 0}; // 우 좌 상 하
	
	static class Player {
		int idx, r, c, dir;

		public Player(int idx, int r, int c, int dir) {
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_17780_새로운게임.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		color = new int[N+1][N+1];
		qmap = new ArrayDeque[N+1][N+1];
		players = new Player[K];

		for(int i=1;i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=N; j++) {
				qmap[i][j] = new ArrayDeque<Integer>();
				color[i][j] = stoi(st.nextToken());
			}
		}
		
		int r, c, d, idx=-1;;
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			r = stoi(st.nextToken());
			c = stoi(st.nextToken());
			d = stoi(st.nextToken())-1;
			players[i] = new Player(++idx, r, c, d);
			qmap[r][c].offer(idx);
		}

		System.out.println(solve());
		br.close();
	}
	
	static int flipDir(int d) {
		if(d==0) return 1;
		else if(d == 1) return 0;
		else if(d == 2) return 3;
		else if(d == 3) return 2;
		return -1;
	}
	
	static int solve() {
		// 1. 1번 턴부터
		// 1000번을 넘어가면 -1 출력
		boolean res;
		for(int i=1; i<=1000; i++) {
			res = turn();
			if(res) return i;
		}
		return -1;
	}
	
	// 2. 턴마다 말 이동
	static boolean turn() {
		// 0번부터 K-1번째 말
		
		int r, c, idx, nr, nc;
		for(int i=0; i<K; i++) {
			//Player cur = players[i];
			idx = players[i].idx;
			r = players[i].r;			// 제일 위에 있는 말인지 확인
			c = players[i].c;	
			if(qmap[r][c].peek() != idx) continue;

			nr = r + dr[players[i].dir];
			nc = c + dc[players[i].dir];
			
			int dd=0;

			if(nr<1||nr>N||nc<1||nc>N) {			
				while(dd++<4) {
					players[i].dir = flipDir(players[i].dir);
					nr = r + dr[players[i].dir];
					nc = c + dc[players[i].dir];
					if(!(nr<1||nr>N||nc<1||nc>N)) break;
				}
			}

			// 3. 이동
			// 방향 바꿨는데도 범위 벗어남
			if(nr<1||nr>N||nc<1||nc>N) continue;
			
			if(color[nr][nc]==0) {
				if(moveToWhile(r, c, nr, nc)) return true;
			} else if(color[nr][nc]==1) {
				if(moveToRed(r, c, nr, nc)) return true;
			} else if(color[nr][nc]==2) {
				players[i].dir = flipDir(players[i].dir);
				nr = r + dr[players[i].dir];
				nc = c + dc[players[i].dir];
				
				if(nr<1||nr>N||nc<1||nc>N|| color[nr][nc]==2) {
					// 방향 바꿨는데 또 파란색
					continue;
				} else if(color[nr][nc]==0) {
					if(moveToWhile(r, c, nr, nc)) return true;
				} else if(color[nr][nc]==1) {
					if(moveToRed(r, c, nr, nc)) return true;
				}
			}
		}
		return false;
	}
	
	// 3-1. 이동
	static boolean moveToWhile(int r, int c, int nr, int nc) {
		// 모든 말이 이동
		int curIdx=0;
		while(!qmap[r][c].isEmpty()) {
			curIdx= qmap[r][c].poll();
			// nr, nc로 이동
			players[curIdx].r = nr;
			players[curIdx].c = nc;
			
			qmap[nr][nc].offer(curIdx);
		}
		
		if(qmap[nr][nc].size()>=4) {
			// 중간에 4개가 넘어가도 멈추려면 size()==4이 아니라 >=
			return true;
		}
		else return false;
	}
	
	static boolean moveToRed(int r, int c, int nr, int nc) {
		// 모든 말이 이동
		
		// 이동 전에 순서를 바꾼다.
		Stack<Integer> stack = new Stack<Integer>();
		int curIdx=0;
		while(!qmap[r][c].isEmpty()) {
			curIdx= qmap[r][c].poll();
			// nr, nc로 이동
			players[curIdx].r = nr;
			players[curIdx].c = nc;
			stack.add(curIdx);
		}
		
		while(!stack.isEmpty()) {
			qmap[nr][nc].offer(stack.pop());
		}
		
		if(qmap[nr][nc].size()>=4) {
			// 중간에 4개가 넘어가도 멈추려면 size()==4이 아니라 >=
			return true;
		}
		else return false;
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
