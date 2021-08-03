import java.io.*;
import java.util.*;
// 210326

public class Main_BJ_2644_촌수계산 {
	static int N, M;
	static ArrayList<Integer>[] list;
	static int start, end;
	
	static int bfs(int from) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] v = new boolean[N+1];
		q.offer(from);
		v[from] = true;
		
		int level = 0;
		int cur;
		int size;
		
		while(!q.isEmpty()) {
			++level;
			size = q.size();
			// q의 size만큼 poll 하여 자식 노드들을 탐색한다.
			for(int i=0; i<size; i++) {
				cur = q.poll();
				// cur과 연결된 자식들
				for(int next: list[cur]) {
					// 목표 노드까지 도달함
					if(next == end) return level;
					
					if(v[next]) continue;
					q.offer(next);
					v[next] = true;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_BJ_2644_촌수계산.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		list = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine(), " ");
		start = stoi(st.nextToken());
		end = stoi(st.nextToken());
		
		M = stoi(br.readLine());
		
		int x, y;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = stoi(st.nextToken());
			y = stoi(st.nextToken());
			list[x].add(y);
			list[y].add(x);
		}
		
		System.out.println(bfs(start));
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
