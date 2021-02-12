import java.io.*;
import java.util.*;

//210212

public class Main_BJ_1260_DFS와BFS {
	static int N, M; //정점 수 N, 간선 수 M
	static int[][] map;
	static int[] visit;
	static Queue<Integer> queue;
	static StringBuilder sb = new StringBuilder();
	
	static void DFS(int v) {
		visit[v] = 1;
		sb.append(v + " "); 
		
		for(int i=1; i<=N; i++) {
			if (map[v][i]==1 && visit[i]==0) {
				visit[i] = 1;
				DFS(i);
			}		
		}
	} // 

	static void BFS(int v) {
		// 루트 노드 방문
		visit[v] = 1;
		sb.append(v + " ");
		queue.offer(v);
		
		while(!queue.isEmpty()) {
			v = queue.poll();
			// 큐에서 앞에서부터 꺼내며 자식 노드들 방문
			for(int i=1; i<=N; i++) {
				if (map[v][i]==1 && visit[i]==0) {
					// 자식 노드 방문
					visit[i] = 1;
					sb.append(i + " ");
					queue.offer(i);
				}
			}
		}
	} // 
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_1260_DFS와BFS.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 정점 수
		M = sc.nextInt(); // 간선 수
		int start = sc.nextInt(); // 탐색을 시작할 정점 번호
		
		map = new int[N+1][N+1];
		
		for(int i=0;i<M; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			
			map[v1][v2] = 1;
			map[v2][v1] = 1;
		}
		
		visit = new int[N+1];
		DFS(start);
		
		sb.append("\n");
		queue = new LinkedList<Integer>();
		visit = new int[N+1];
		BFS(start);
		
		System.out.println(sb.toString());
		sc.close();
	} //
}
