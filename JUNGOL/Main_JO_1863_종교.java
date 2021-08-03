import java.io.*;
import java.util.*;
// 210318

public class Main_JO_1863_종교 {
	static int N, M, parents[];
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void makeSet() {
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a]==a) return a;
		else return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	static int check() {
		int cnt=0;
		for(int i=1; i<=N; i++) {
			if(parents[i]==i) ++cnt; 
			// 자신의 집합의 대표자가 자신인 경우
		}
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_JO_1863_종교.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		
		N = stoi(st.nextToken()); // 정점 수
		M = stoi(st.nextToken()); // 간선 수
		
		parents = new int[N+1];
		
		makeSet();
		
		int a, b;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			a =  stoi(st.nextToken());
			b = stoi(st.nextToken());
			union(a, b);
		}
		
		System.out.println(check());
		br.close();
	}
}
