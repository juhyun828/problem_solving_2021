import java.io.*;
import java.util.*;
// 210318

public class Solution_D4_1486_장훈이의높은선반 {
	static int N, B, min;
	static int[] height;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void dfs(int L, int total) {
		
		if(total > min) return;
		
		if(total >= B) {
			min = Integer.min(min, total);
			return;
		}
		
		if(L==N) {
			if(total>=B) {
				min = Integer.min(min, total);
			}
			
			return;
		}
		
		// 선택
		dfs(L+1, total+height[L]); 
		// 비선택
		dfs(L+1, total);
		
		/* 백트래킹식
			sum += height[L];
			dfs(L+1);
			sum -= height[L];
			dfs(L+1); 
		*/
		
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_1486_장훈이의높은선반.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			st=new StringTokenizer(br.readLine().trim(), " ");
			N = stoi(st.nextToken());
			B = stoi(st.nextToken());
			min = Integer.MAX_VALUE;
			
			height = new int[N];

			st=new StringTokenizer(br.readLine().trim(), " ");
			for(int i=0; i<N; i++) {
				height[i] = stoi(st.nextToken());
			}
			
			dfs(0, 0);
			sb.append("#" + tc + " " + (min-B) + "\n");
		}
		System.out.println(sb.toString());		
		br.close();
	}

}
