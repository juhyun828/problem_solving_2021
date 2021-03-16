import java.io.*;
import java.util.*;
// 210316

public class Main_BJ_1260_DFS와BFS {
	static int N, M, V; // 정점 개수, 간선 개수, 시작 정점 번호
	// 양방향 그래프
	static int[][] map;
	static boolean[] visited;
	static Queue<Integer> q;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_1260_DFS와BFS.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		V = stoi(st.nextToken());
		
		map = new int[N+1][N+1];
		q = new ArrayDeque<Integer>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			map[from][to] = map[to][from] = 1;
		}
		
		visited = new boolean[N+1];
		dfs(V);
		
		System.out.println();
		visited = new boolean[N+1];
		bfs(V);
		
		br.close();
	}
	
	static void dfs(int v) {
		visited[v] = true;
		System.out.print(v + " ");
		
		for(int i=1; i<=N; i++) {
			if(map[v][i]==1 && !visited[i]) {
				dfs(i);
			}
		}
	}
	
	static void bfs(int v) {
		visited[v] = true;
		q.offer(v);
		
		while(!q.isEmpty()) {
			int p = q.poll();
			System.out.print(p + " ");
	
			for(int i=1; i<=N; i++) {
				if(map[p][i]==1 && !visited[i]) {
					visited[i] = true;
					q.offer(i);
				}
			}
		}
	}
}
