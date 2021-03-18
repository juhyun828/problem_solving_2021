import java.io.*;
import java.util.*;
// 210318

public class Solution_D4_3289_서로소집합 {
	static int N, M;
	static int[] parents;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void make() {
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a]==a) return a; // 자기 자신이 부모 
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) { // a의 집합에 b 집합을
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot==bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_D4_3289_서로소집합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			N = stoi(st.nextToken()); // 1 ~N까지의 집합
			M = stoi(st.nextToken()); // 명령 개수
			parents = new int[N+1];
			
			make(); 
			
			int op=0; // 0 교 1 합
			int a =0, b=0;
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				op = stoi(st.nextToken());
				a = stoi(st.nextToken());
				b = stoi(st.nextToken());
				
				if(op==0) {
					union(a, b);
				} else if(op==1) {
					if(findSet(a)==findSet(b)) sb.append("1");
					else sb.append("0");
				} 
			
			} // 
			
			sb.append("\n");
			
		} // tc

		System.out.println(sb.toString());
	}

}
