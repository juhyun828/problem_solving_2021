package day0324;
import java.io.*;
import java.util.*;
// 210324

public class Solution_D4_1219_길찾기 {
	static int N, ans;
	static ArrayList[] adj;
	static boolean[][] v;
	static final int END = 99;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_D4_1219_길찾기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = 10;
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			st.nextToken(); // tc
			N = stoi(st.nextToken());
			adj = new ArrayList[END+1];
			ans = 0;
			v = new boolean[END+1][END+1];
			
			// 인접 리스트
			for(int i=0; i<=END; i++) {
				adj[i] = new ArrayList<Integer>();
			}
			
			// 순서쌍 입력
			st = new StringTokenizer(br.readLine(), " ");
			int from, to;
			for(int i=0; i<N; i++) {
				from = stoi(st.nextToken());
				to = stoi(st.nextToken());
				adj[from].add(to);
			}
			dfs(0);
			sb.append("#" + tc + " "+ ans + "\n");
		} // tc
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void dfs(int from) {
		if (ans==1) return;
		if(from == END) {
			ans = 1;
			return;
		}
		
		for(int i=0; i<adj[from].size(); i++) {
			int to = (Integer) adj[from].get(i);
			if(v[from][to]) continue;
			v[from][to] = true;
			dfs(to);
			v[from][to] = false;
		}
		
	}

}