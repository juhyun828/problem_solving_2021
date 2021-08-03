import java.io.*;
import java.util.*;
// 210317

public class Main_BJ_14889_스타트와링크 {
	static int N, min, map[][];
	static boolean[] isSelected;
	static int[] A, B;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}	
	
	static void combination(int cnt, int L) {
		if(cnt==N/2) {
			int ai=-1, bi=-1;
			for(int i=0; i<N; i++) {
				if(isSelected[i]) A[++ai] = i;
				else B[++bi] = i;
			}	
			cal();
			return;
		}
		if(L==N) return;
		
		isSelected[L] = true;
		combination(cnt+1, L+1);
		isSelected[L] = false;
		combination(cnt, L+1);
		
	}
	
	static void cal() {
		int ap=0, bp=0; // A팀 점수, B팀 점수
		
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<N/2; j++) {
				ap += map[A[i]][A[j]];
				bp += map[B[i]][B[j]];
			}
		}
		min = Math.min(min, Math.abs(ap-bp));
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_14889_스타트와링크.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = stoi(br.readLine().trim());
		map = new int[N][N];
		isSelected = new boolean[N];
		min = Integer.MAX_VALUE;
		A = new int[N/2]; B = new int[N/2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		combination(0, 0);
		System.out.println(min);
		br.close();
	}
}
