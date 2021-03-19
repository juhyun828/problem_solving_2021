import java.io.*;
import java.util.*;
// 210319

public class Main_BJ_17471_게리멘더링_2 {
	static int N, min;	
	static int[] p;
	static int[][] map;
	static int[] teams;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void dfs(int L) {
		if(L == (N+1)) {
		
			if(isConnected(0) && isConnected(1)) {
				int diff = getCount();
				min = Integer.min(min, diff);
			}
			
			return;
		}
		
		teams[L] = 0;    // L번째 구역을 0번팀에
		dfs(L+1);
		teams[L] = 1;	// L번째 구역을 1번팀에
		dfs(L+1);
		
	}
	
	static boolean isConnected(int type) {
		// bfs
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] v = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			if(teams[i] == type) {
				q.offer(i);
				v[i] = true;
				break; // 첫번째로 연결된 지역만 찾는다.
			}
		}
		
		if(q.size()==0) return false;
		
		int cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			for(int i=1;i<=N; i++) {
				if(map[cur][i]==0) continue; // 연결x
				if(v[i]) continue; // 이미 방문
				if(teams[i]!=type) continue; // 다른 팀
				q.offer(i);
				v[i] = true;
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(teams[i]!=type) continue;
			// type 팀인 구역들이 모두 방문되지 않았다면 연결되지 않은 구역
			if(!v[i]) return false;
		}
		return true;
	}
	
	static int getCount() {
		int team1 = 0, team2 = 0;
		
		for(int i=1; i<=N; i++) {
			if(teams[i]==0) team1 += p[i];
			else team2 += p[i];
		}
		
		return Math.abs(team1-team2);
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_BJ_17471_게리멘더링.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine().trim());
		min = Integer.MAX_VALUE;
		p = new int[N+1]; // 1, 2,, N 번째 구역 인구 수
		map = new int[N+1][N+1];
		teams = new int[N+1];
		
		st = new StringTokenizer(br.readLine().trim(), " ");
		for(int i=1; i<=N; i++) {
			p[i] = stoi(st.nextToken());
		}
		
		int  n, to;
		for(int from=1; from<=N; from++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			n = stoi(st.nextToken());
			for(int i=0; i<n; i++) {
				to = stoi(st.nextToken());
				map[from][to] = 1;
				map[to][from] = 1;
			}
			
		}
		
		dfs(1);
	
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
		br.close();
	}

}
