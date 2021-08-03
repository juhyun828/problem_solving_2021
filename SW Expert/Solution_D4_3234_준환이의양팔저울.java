import java.io.*;
import java.util.*;

// 210219 

public class Solution_D4_3234_준환이의양팔저울 {
	static int N;
	static int[] w;
	static int cnt;
	static int[] res;

	static void permutation(int cnt, boolean[] isSelected) {
		if (cnt==N) {
			//System.out.println(Arrays.toString(res));
			pose(res,0,0,0);
			return;
		}
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			res[cnt] = w[i];
			permutation(cnt+1, isSelected);
			isSelected[i] =false;
			
		}
	}
	
	static void pose(int[] res, int idx, int r, int l) {
		if (r < l) return;
		
		if (idx==N) {
			++cnt;
			return;
		}
		
		// 왼쪽에 놓거나
		pose(res, idx+1, r+res[idx], l);
		// r -= res[idx] // static일 때 (시간초과)
		
		// 오른쪽에 넣거나
		pose(res, idx+1, r, l+res[idx]);
	}
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_D4_3234_준환이의양팔저울.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
		
			N = stoi(br.readLine());
			w = new int[N];
			res = new int[N];
			cnt = 0;
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				w[i] = stoi(st.nextToken());
			}
			//System.out.println(Arrays.toString(w));
			permutation(0, new boolean[N]);
			System.out.println("#" + tc + " " + cnt);
		}

		br.close();
	}//

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
