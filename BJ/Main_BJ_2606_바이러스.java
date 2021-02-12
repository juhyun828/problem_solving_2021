import java.io.*;
import java.util.*;
// 210212

public class Main_BJ_2606_바이러스 {
	static int N, M;
	static int[][] map;
	static boolean[] visit;
	static int cnt = 0;
	
	static void DFS(int v) {
		visit[v] = true;
		
		for(int i=1; i<=N; i++) {
			if (map[v][i]==1 && !visit[i]) {
				visit[i] = true;
				cnt++;
				DFS(i);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_2606_바이러스.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 컴퓨터 수 - 노드 수
		M = Integer.parseInt(br.readLine()); // 네트워크 쌍의 수 - 정점 수
		map = new int[N+1][N+1]; // 컴퓨터는 1번부터
		visit = new boolean[N+1];

		StringTokenizer st;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			map[v1][v2] = 1;
			map[v2][v1] = 1;
		}
		
		DFS(1);
		System.out.println(cnt);
		
		br.close();
	} // 

}
