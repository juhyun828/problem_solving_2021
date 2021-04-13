import java.io.*;
import java.util.*;
// 210413

public class Main_BJ_19238_스타트택시_fail {
	static int N, M, V;
	static int tr, tc, v; // 택시의 위치 정보
	static PriorityQueue<Customer> pq;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Customer implements Comparable<Customer> {
		int idx;
		int sr, sc, er, ec;
		int taxiDist, targetDist;
		
		public Customer(int idx, int sr, int sc, int er, int ec, int taxiDist, int targetDist) {
			this.idx = idx;
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
			this.taxiDist = taxiDist;
			this.targetDist = targetDist;
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
	
	static class Pos implements Comparable<Pos>{
		int r, c, dist;

		public Pos(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Pos o) {
			if(this.dist==o.dist) {
				if(this.r==o.r) 
					return Integer.compare(this.c, o.c);
				return Integer.compare(this.r, o.r);
			}
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	static int getDist(int sr, int sc, int er, int ec) {
		
		boolean[][] v = new boolean[N+1][N+1];
		PriorityQueue<Pos> q = new PriorityQueue<Pos>();
		
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
		// 택시가 갈 수 없는 경우
		return Integer.MAX_VALUE;
	}
	
	static int solve() {
		
		for(int i=0; i<M; i++) {
			if(!move()) return -1;		
			chageTaxiDistance(); // 움직인 택시와의 거리 새로 구한다.
		}
		
		return v;
	}
	
	static void chageTaxiDistance() {
		
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
		
		Customer cur = pq.poll();
		if(cur.taxiDist==Integer.MAX_VALUE) return false;

		// 1. taxi->손님 시작지
		v -= cur.taxiDist;
		if(v<0) return false;
		
		// 2. 손님 시작지 -> 손님 도착지
		v -= cur.targetDist;
		if(v<0) return false;
		
		// 3. 연료 두배
		v += cur.targetDist*2;
		tr = cur.er;
		tc = cur.ec;
		
		return true;
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
		int r1, c1, r2, c2, taxiDist, targetDist;
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			r1 = stoi(st.nextToken());
			c1 = stoi(st.nextToken());
			r2 = stoi(st.nextToken());
			c2 = stoi(st.nextToken());
			taxiDist = getDist(r1, c1, tr, tc);
			targetDist = getDist(r1, c1, r2, c2);
			pq.offer(new Customer(i, r1, c1, r2, c2, taxiDist, targetDist));
		}
		
		System.out.println(solve());

		
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
