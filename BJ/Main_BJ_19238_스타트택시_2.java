import java.io.*;
import java.util.*;
// 210421

public class Main_BJ_19238_스타트택시_2 {
	static int N, M, V;
	static int[][] map; // 0: 빈칸, 1: 벽
	static int taxiR, taxiC;
	static ArrayList<Customer> customers;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Customer {
		int idx;
		int sr, sc, er, ec, taxiDist;
		boolean finished = false;
		
		public Customer(int idx, int sr, int sc, int er, int ec) {
			this.idx = idx;
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
		}
		
		public Customer(int idx, int sr, int sc, int er, int ec, int taxiDist) {
			this.idx = idx;
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
			this.taxiDist = taxiDist;
		}
	}
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_19238_스타트택시.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		V = stoi(st.nextToken());
		
		map = new int[N+1][N+1];
		customers = new ArrayList<Customer>();
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=N; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		// 택시 정보
		st = new StringTokenizer(br.readLine(), " ");
		taxiR = stoi(st.nextToken());
		taxiC = stoi(st.nextToken());
		
		// 손님 정보
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sr = stoi(st.nextToken());
			int sc = stoi(st.nextToken());
			int er = stoi(st.nextToken());
			int ec = stoi(st.nextToken());
			customers.add(new Customer(i, sr, sc, er, ec));
//			map[sr][sc] = 2; 
			// bfs에서 그 손님을 지나쳐 갈 필요가 없다. 지나치게 되는 손님이 더 가까움
		}
			
		solve();
		
		br.close();
	}
	
	static void solve() {
		//1. M번 시도
		for(int t=1; t<=M; t++) {
			boolean stop = selectCustomer();
			if(stop) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(V);
	}
	
	// 2. 택시가 택할 손님 찾기 시도
	static boolean selectCustomer() {
		ArrayList<Customer> list = new ArrayList<Customer>();
		int[][] v = getAllDist(); // 현재 택시 위치에서 각 칸까지의 거리를 bfs를 한번만 써서 구한다.
		
		for(int i=0; i<M; i++) {
			Customer c = customers.get(i);
			if(c.finished) continue;
			int taxiDist = v[c.sr][c.sc];
			if(taxiDist==-1) continue;
			list.add(new Customer(c.idx, c.sr, c.sc, c.er, c.ec, taxiDist));
		}
	
		if(list.size()==0) return true;
		
		// 가장 가까운 / 열이 작은 손님 택
		Collections.sort(list, new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				if(o1.taxiDist == o2.taxiDist) {
					if(o1.sr == o2.sr)
						return Integer.compare(o1.sc, o2.sc);
					return Integer.compare(o1.sr, o2.sr);
				}
				return Integer.compare(o1.taxiDist, o2.taxiDist);
			}
		});
		Customer c = list.get(0);
		
		// 3. 택시 이동
		// 손님 출발지까지 택시 이동
		V -= c.taxiDist;
		if(V<0) return true;
		
		// 손님 출발지에서 도착지까지 이동
		int targetDist = getDist(c.sr, c.sc, c.er, c.ec);
        if(targetDist==Integer.MAX_VALUE) return true;
        
		V -= targetDist;
		if(V<0) return true;
		V += targetDist*2;
		
		// 택시 위치 변경
		taxiR = c.er;
		taxiC = c.ec;
		
		// 손님 완료 처리
		customers.get(c.idx).finished = true;
		
		return false;
	}

	// 2. 벽을 피해가며 택시로부터 각 칸까지의 거리 구하기
	static int[][] getAllDist() {
		
		int[][] v = new int[N+1][N+1];
		for(int i=0; i<=N; i++)
			Arrays.fill(v[i], -1);
		Queue<Pos> q = new ArrayDeque<Pos>();
		
		// 시작지점 (택시 위치)
		v[taxiR][taxiC] = 0;
		q.offer(new Pos(taxiR, taxiC));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr<1 || nr>N || nc<1 || nc>N || map[nr][nc]!=0 || v[nr][nc]!=-1) continue;
				
				v[nr][nc] = v[cur.r][cur.c] + 1;
				q.offer(new Pos(nr, nc));
			}	
		}
		
		return v;
	}
	
	
	// 3. 벽을 피해가며 두 점 사이의 거리 구하기
	static int getDist(int r1, int c1, int r2, int c2) {
		// 벽과 손님들을 피해가며 거리 구하기
		int[][] v = new int[N+1][N+1];
		for(int i=0; i<=N; i++)
			Arrays.fill(v[i], -1);
		Queue<Pos> q = new ArrayDeque<Pos>();
		
		// 시작지점 : 손님 출발지
		v[r1][c1] = 0;
		q.offer(new Pos(r1, c1));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if(cur.r == r2 && cur.c == c2)
				return v[cur.r][cur.c];
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr<1 || nr>N || nc<1 || nc>N || map[nr][nc]!=0 || v[nr][nc]!=-1) continue;
				
				v[nr][nc] = v[cur.r][cur.c] + 1;
				q.offer(new Pos(nr, nc));
			}
		}
		return Integer.MAX_VALUE;
	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
