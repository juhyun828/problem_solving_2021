import java.io.*;
import java.util.*;

// 210214

public class Main_BJ_1707_이분그래프 {
	static int V, E;
	static int[][] map;
	static int[] colors;
	static int curColor;
	static int[][] arr;
	static ArrayList< ArrayList<Integer>> adj;
	static String ans;
 
	static boolean dfs(int v, int color) {
		colors[v] = color;
		
		for(Integer i: adj.get(v)) {
			if(colors[i] == 0) {// 색칠 x
				colors[i] = -color;
				if(!dfs(i, -color))
					return false;
			} else { // 색칠 o
				if(color==colors[i]) { // 같은 색
					ans = "NO";
					return false;
				} 
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adj = new ArrayList<>();
			// 정점 수 만큼
			for(int i=0; i<V+1; i++) {
				adj.add(new ArrayList<Integer>());
			}
			
			colors = new int[V+1];
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				
				adj.get(v1).add(v2);
				adj.get(v2).add(v1);
				
			}
			
			ans = "YES";
			for(int i=1; i<V+1; i++) {
				if (colors[i]==0) {
					if(!dfs(i, 1))
						break;
				}
			}
			System.out.println(ans);
 		} // tc
		
		br.close();
	}
}
