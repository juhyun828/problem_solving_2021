import java.io.*;
import java.util.*;
// 210316

public class Solution_D4_1238_Contact {
	static int N, start, visited[];
	static int[][] map;
	static Queue<Integer> q;

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void bfs() {
		while(!q.isEmpty()) {
			int p = q.poll();

			for(int i=1; i<=N; i++) {
				if(i==start) continue;
				if(map[p][i]==1 && visited[i]==0) {
					visited[i] = visited[p] + 1;
					q.offer(i);
				}
			}	
		}
	}
	
	static int check() {
		int maxDay = 0; // 가장 늦게 연락 받는 사람 중
		int maxIdx = 0; // 가장 큰 번호
		
		for(int i=1; i<=N; i++) {
			if(maxDay < visited[i]) {
				maxDay = visited[i];
				maxIdx = i;
			} else if(maxDay==visited[i]) {
				maxIdx = Math.max(maxIdx, i);
			}
			
		}
		return maxIdx;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_D4_1238_Contact.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = 10; 
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = stoi(st.nextToken());
			start = stoi(st.nextToken());
			
			map = new int[N+1][N+1];
			visited =  new int[N+1];
			q = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<=N/2; i++) {
				int from = stoi(st.nextToken());
				int to = stoi(st.nextToken());
				map[from][to] = 1;
			}
			
			for(int i=1;i<=N; i++) {
				if(map[start][i]==1) {
					// visited & enqueue 같이 하기
					visited[i] = 1;
					q.offer(i);
				}
			}
			bfs();
			sb.append("#" + tc + " " + check() + "\n");
		}
		
		System.out.println(sb.toString());

		br.close();
	}
}
