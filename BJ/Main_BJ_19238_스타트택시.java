import java.io.*;
import java.util.*;
// 210413

public class Main_BJ_19238_스타트택시 {
	static int N, M, V;
	static int tr, tc, v; // 택시의 위치 정보
	static PriorityQueue<Customer> pq;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Customer implements Comparable<Customer> {
		int idx;
		int sr, sc, er, ec;
		int taxiDist;
		
		public Customer(int idx, int sr, int sc, int er, int ec, int taxiDist) {
			this.idx = idx;
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
			this.taxiDist = taxiDist;
		}

		@Override
		public int compareTo(Customer o) {
			if(this.taxiDist==o.taxiDist) {
				if(this.sr==o.sr) 
					return Integer.compare(this.sc, o.sc);
				return Integer.compare(this.sr, o.sr);
			}
			return Integer.compare(this.taxiDist, o.taxiDist);
		}
		
	}
	
	static class Pos {
		int r, c, dist;

		public Pos(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		
	}
	
	static int getDist(int sr, int sc, int er, int ec) {
		
		boolean[][] v = new boolean[N+1][N+1];
		Queue<Pos> q = new ArrayDeque<Pos>();
		// BFS에서 우선순위 큐 남발하지 않기^^
		// 가중치가 동일한 그래프라면 최초로 도착지에 도착한 경우가 최단거리다.
		// 굳이 우선순위큐로 거리값을 관리할 필요 없음
		
		// 시작점
		v[sr][sc] = true;
		q.offer(new Pos(sr, sc, 0));
		
		Pos cur;
		int nr, nc;
		while(!q.isEmpty()) {
			cur = q.poll();
			if(cur.r==er && cur.c==ec)
				return cur.dist;
			
			for(int d=0; d<4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				if(nr<1 || nr>N || nc<1 || nc>N || v[nr][nc] || map[nr][nc]==1) continue;
				v[nr][nc]=true;
				q.offer(new Pos(nr, nc, cur.dist+1));
			}
		}
		// 택시가 갈 수 없는 경우 -> 우선순위 큐에서 제일 마지막 순위가 되도록 최대값을 반환
		return Integer.MAX_VALUE;
	}
	
	static void chageTaxiDistance() {
		// 1. 현재 택시 위치와의 거리 새로 구한다.
		
		// 새롭게 PQ에 값을 갱신하며 넣어야
		// 바뀐 택시 거리에 대한 거리값을 기준으로 우선순위큐 순서가 정해진다.
		PriorityQueue<Customer> tmpPq = new PriorityQueue<Customer>();
		
		Customer cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			cur.taxiDist = getDist(cur.sr, cur.sc, tr, tc);
			tmpPq.offer(cur);
		}
		
		pq = tmpPq;
	}
	
	static boolean move( ) {
		
		// 2. 가장 가까이 있는 손님부터 시도
		Customer cur = pq.poll();
		if(cur.taxiDist==Integer.MAX_VALUE) return false;

		// 1). taxi->손님 시작지
		v -= cur.taxiDist;
		if(v<0) return false;
		
		// 2). 손님 시작지 -> 손님 도착지
		int targetDist = getDist(cur.sr, cur.sc, cur.er, cur.ec);
		if(targetDist==Integer.MAX_VALUE) return false;
		
		v -= targetDist;
		if(v<0) return false;
		
		// 3). 연료 두배
		v += targetDist*2;
		tr = cur.er;
		tc = cur.ec;
		
		return true;
	}
	
	static int solve() {
		for(int i=0; i<M; i++) {
			// 1. 현재 택시 위치와의 거리 새로 구한다.
			chageTaxiDistance(); 
			// 2. 가장 가까이 있는 손님부터 시도
			if(!move()) return -1;		
		}
		
		return v;
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_19238_스타트택시.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		V = stoi(st.nextToken());
		
		// 지도
		map = new int[N+1][N+1];
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=1; c<=N; c++) {
				map[r][c] = stoi(st.nextToken());
			}
		}
		
		// 택시 정보
		v = V;
		st = new StringTokenizer(br.readLine(), " ");
		tr = stoi(st.nextToken());
		tc = stoi(st.nextToken());
		
		// 손님 정보
		pq = new PriorityQueue<Customer>();
		
		Customer c = null;
		int r1, c1, r2, c2, taxiDist;
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			r1 = stoi(st.nextToken());
			c1 = stoi(st.nextToken());
			r2 = stoi(st.nextToken());
			c2 = stoi(st.nextToken());
			pq.offer(new Customer(i, r1, c1, r2, c2, 0));
		}
		
		System.out.println(solve());
		
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
