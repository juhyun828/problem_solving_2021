import java.io.*;
import java.util.*;
// 210314

public class Solution_모의_4012_요리사_2 {
	
	static int N, map[][], min;
	static ArrayList<Integer> A, B;
	static boolean[] visited;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	// N C (N/2)
	static void subset(int cnt, int L) {
		
		if(cnt>N/2) {
			A = new ArrayList<>(); B = new ArrayList<>();
			for(int i=1; i<=N; i++) {
				if(visited[i]) A.add(i);
				else B.add(i);
			}

			min = Math.min(calPoints(), min);
			return;
		}
		
		if(L>N) {
			return;
		}
		
		visited[L] = true;
		subset(cnt+1, L+1);
		visited[L] = false;
		subset(cnt, L+1);
	}

	static int calPoints() {
		int pointA=0, pointB=0;
		
		for(int i=0; i<A.size(); i++) {
			for(int j=0; j<A.size(); j++) {
				pointA += map[A.get(i)][A.get(j)];
			}
		} 
		for(int i=0; i<B.size(); i++) {
			for(int j=0; j<B.size(); j++) {
				pointB += map[B.get(i)][B.get(j)];
			}
		} 
		
		return Math.abs(pointA-pointB);
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_모의_4012_요리사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			N = stoi(br.readLine().trim());
			
			map = new int[N+1][N+1]; // 음식은 1번부터 시작
			min = Integer.MAX_VALUE;
			visited = new boolean[N+1];
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=1; j<=N; j++) {
					map[i][j] = stoi(st.nextToken());
				}
			}
			
			subset(1, 1);
			sb.append("#" + tc + " " + min + "\n");
		}// tc
		
		System.out.println(sb.toString());
		br.close();
	}
}
