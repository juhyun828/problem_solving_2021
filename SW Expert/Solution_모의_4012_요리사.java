import java.io.*;
import java.util.*;

// 210219 

public class Solution_모의_4012_요리사 {
	static int N;
	static int[][] s;
	static int[] a, b;
	static int min;
	static boolean[] isSelected;
	
	static void combination(int cnt, int start) {
		if(cnt==N/2) {
			int ia=-1, ib=-1;
			for(int i=0; i<N; i++) {
				if(isSelected[i]) a[++ia]=i;
				else b[++ib]=i;
			}
			int sumA=cal(a), sumB=cal(b);
			min = Math.min(min, Math.abs(sumA-sumB));
			return;
		}
		
		for(int i=start; i<N; i++) {
			isSelected[i]=true;
			combination(cnt+1, i+1);
			isSelected[i]=false;
		}
	}
	
	static int cal(int[] arr) {
		int sum = 0;
		for(int k=0; k<arr.length; k++) {
			for(int z=k+1; z<arr.length; z++) {
				int i = arr[k], j =arr[z]; 
				sum += s[i][j];
				sum += s[j][i];
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_모의_4012_요리사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			N = stoi(br.readLine());
			s = new int[N][N]; // 음식 맛 시너지
			a = new int[N/2]; b = new int[N/2];
			min=Integer.MAX_VALUE;
			isSelected = new boolean[N];
			
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c=0; c<N; c++) {
					s[r][c] = stoi(st.nextToken());
				}
			}
			combination(0, 0);
			System.out.println("#" + tc + " " + min);
		}
		br.close();
	}//

	static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
