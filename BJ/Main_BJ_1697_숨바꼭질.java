import java.io.*;
import java.util.*;

// 210213

public class Main_BJ_1697_숨바꼭질 {
	static int N, K;
	static int[] dist;
	static Queue<Integer> q;
	
	static void bfs() {
		while (!q.isEmpty()) {
			int x = q.poll();
			
			for(int d=0; d<3; d++) {
				int nx = x;
				if (d==0)
					nx = x - 1;
				else if (d==1) 
					nx = x + 1;
				else if (d==2) 
					nx = x * 2;
		
				if (nx>=0 && nx<=100000 && dist[nx]==-1) {
					dist[nx] = dist[x] + 1;
					q.offer(nx);
				}
				if (nx==K) return;
			}
		} // 
	} // 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치
		
		dist = new int[100001];
		Arrays.fill(dist, -1);
		dist[N] = 0;
		q = new ArrayDeque<Integer>();
		q.offer(N);
		
		
		if (N==K) System.out.println(0);
		else {
			bfs();
			System.out.println(dist[K]);
		}
		
		br.close();
	}
}