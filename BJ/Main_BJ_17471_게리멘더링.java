import java.io.*;
import java.util.*;
// 210318

public class Main_BJ_17471_게리멘더링 {
	static int N; // 도시 수
	static int[] p; // 인구 수
	static int[][] map; // 도시 연결 관리 -> 인접 행렬
	static int[] teams;
	static int min = Integer.MAX_VALUE;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_BJ_17471_게리멘더링.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = stoi(br.readLine().trim());
		p = new int[N+1];
		map = new int[N+1][N+1];
		teams = new int[N+1];
		
		st = new StringTokenizer(br.readLine().trim(), " ");
		for(int i=1; i<=N; i++) {
			p[i] = stoi(st.nextToken());
		}
		
		int cnt=0;
		int k;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			cnt = stoi(st.nextToken());
			for(int j=0; j<cnt; j++) {
				k = stoi(st.nextToken()); // i->k 연결
				map[i][k] = 1;
				map[k][i] = 1;
			}
		}
		
		// 솔루션
		dfs(1); // N=1부터 시작
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
		br.close();
	}
	
	// 현재 위치의 도시를 왼쪽에 포함시키거나 / 오른쪽에 포함시키거나
	static void dfs(int idx) { 
		if (idx == (N+1)) { // N=1부터 시작하기 때문에
			// 솔루션 구현
			// A구역, B구역이 각각 연결되어 있는지
			if (check(0) && check(1)) { // 0번들 / 1번들 끼리 연결되어 있나
				int r = getCount(); // 두 도시의 인구수 차이 최소값
				min = Math.min(min, r);
			} 
			return;
		}
		
		teams[idx] = 0; // idx번째를 A팀에
		dfs(idx+1);
		
		teams[idx] = 1; // idx를 B팀에
		dfs(idx+1);
	}
	
	static boolean check(int type) { // 연결되는지 확인 -> bfs
		boolean[] v = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		for(int i=1; i<=N; i++) {
			if(teams[i] == type) {
				v[i] = true;
				q.offer(i);
				break; // 첫번째로 연결된 구역만 찾는다.
			}
		}
		
		if(q.size()==0) {
			// 각 선거구는 적어도 하나의 구역을 포함해야 한다.
			return false;
		}
	
		int cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			for(int i=1; i<=N; i++) { 
				// 연결이 있을 때만 간다.
				if(map[cur][i]==0) continue; // 연결x
				if(v[i]) continue; // 이미 방문/연결된 구역
				if(teams[i]!=type) continue; // 다른 팀
				v[i] = true; // 팀이 같은 i번째는 모두 true여야 한다.
				q.offer(i); // 구역 방문
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(teams[i]!=type) continue;
			if(!v[i]) return false; 
		}
		
		return true;
	}
	
	static int getCount() {
		int team1 = 0;
		int team2 = 0;
		
		for(int i=1; i<=N; i++) {
			if(teams[i]==0) team1 += p[i];
			else team2 += p[i];
		}
		
		return Math.abs(team1-team2);
	}
}
