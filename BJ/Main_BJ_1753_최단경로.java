import java.io.*;
import java.util.*;
// 210322

public class Main_BJ_1753_최단경로 {
	static int V, E, K;
	static int[] dist;
	static boolean[] v;
	static ArrayList<Edge>[] adj;
	static final int INFINITY = Integer.MAX_VALUE;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}	
	
	static class Edge {
		int v, w; // 정잠과 가중치
		
		public Edge(int end, int w) {
			this.v = end;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("res/input_BJ_1753_최단경로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		V = stoi(st.nextToken());
		E = stoi(st.nextToken()); 
		K = stoi(br.readLine().trim());
		adj = new ArrayList[V+1];
		dist = new int[V+1];  Arrays.fill(dist, INFINITY);  dist[K] = 0; // 시작점
		v = new boolean[V+1];
		
		for(int i=1; i<=V; i++) {
			adj[i] = new ArrayList<Edge>();
		}
		
		int from, to, w;
		for(int i=1; i<=E; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			from = stoi(st.nextToken());
			to = stoi(st.nextToken());
			w = stoi(st.nextToken());
			
			adj[from].add(new Edge(to, w));
		}
		
		int min=0, cur=0;
		for(int i=1; i<=V; i++) {
			min = INFINITY; cur=-1;
			for(int j=1; j<=V; j++) {
				if(!v[j] && dist[j] < min) {
					min = dist[j];
					cur = j;
				}
			}
			
			if(cur == -1) break;
			
			for(Edge next : adj[cur]) {
				if(!v[next.v] && dist[next.v] > dist[cur] + next.w) {
					// 방문x 더 작은 경로로 방문할 수 있으면
					dist[next.v] = dist[cur] + next.w;
				}
			}
			v[cur] = true;
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=V; i++) {
			if(dist[i]==INFINITY) sb.append("INF\n");
			else sb.append(dist[i] + "\n");
		}
		System.out.println(sb.toString());
		
		br.close();
	}

}
