import java.io.*;
import java.util.*;
// 210322

public class Main_JO_1681_해밀턴순환회로 {
	static int N, min;
	static int[][] map;
	static boolean[] v;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void dfs(int L, int from, int total) {

		if (total >= min) return;
		if (L==(N-1)) { // 정점은 0~N-1번까지 있음
			if(map[from][0]==0) return;
			min = Math.min(min, total+map[from][0]);
			return;
		}
		
		for(int to=0; to<N; to++) {
			if(v[to]) continue;
			if(map[from][to]==0) continue;
			
			v[to] = true;
			dfs(L+1, to, total+map[from][to]);
			v[to] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_JO_1681_해밀턴순환회로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = stoi(br.readLine().trim());
		min = Integer.MAX_VALUE;
		map = new int[N][N];
		v = new boolean[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		v[0] = true;
		dfs(0, 0, 0);
		System.out.println(min);
		br.close();
	}
}
