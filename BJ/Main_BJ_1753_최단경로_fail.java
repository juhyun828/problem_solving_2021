import java.io.*;
import java.util.*;
// 210322

public class Main_BJ_1753_최단경로_fail {
	static int V, E, K, end;
	static int[] dist;
	static boolean[] v;
	static int[][] map;
	static final int INFINITY = Integer.MAX_VALUE;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		V = stoi(st.nextToken());
		E = stoi(st.nextToken()); end = V; // 도착점 인덱스
		K = stoi(br.readLine().trim());
		map = new int[V+1][V+1];
		dist = new int[V+1];  Arrays.fill(dist, INFINITY); dist[K] = 0; // 시작 위치는 거리 0
		v = new boolean[V+1];
		
		int from, to, w;
		for(int i=1; i<=E; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			from = stoi(st.nextToken());
			to = stoi(st.nextToken());
			w = stoi(st.nextToken());
			if(map[from][to] > 0) map[from][to] = Math.max(w, map[from][to]);
			else map[from][to] = w;	
		}

		int min=0, cur = 0;
		
		for(int i=1; i<=V; i++) {
			// a단계: 방문하지 않은 정점들 중 최소가중치의 정점 선택
			min = INFINITY;
			for(int j=1; j<=V; j++) {
				if(!v[j] && dist[j] < min) {
					min = dist[j];
					cur = j;
				}
			}
			
			v[cur] = true; // 선택 정점 방문 처리
			if(cur == end) break;
			
			// b단계: cur 정점을 경유지로 하여 갈 수 있는 다른 방문하지 않은 정점들 처리
			for(int c=1; c<=V; c++) {
				if(!v[c] && map[cur][c] !=0 && dist[c] > min + map[cur][c]) {
					dist[c] = min + map[cur][c];
				}
			}
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
