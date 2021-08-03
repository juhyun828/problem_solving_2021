import java.io.*;
import java.util.*;

public class Main_BJ_2629_양팔저울_fail {
	static int N, M;
	static int[] weights, balls;
	static boolean[] vl, vr;
	static boolean[] res = new boolean[4001];
	static int left=0, right=0;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 추 무게 입력
		N = stoi(br.readLine());
		weights = new int[N]; 
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			weights[i] = stoi(st.nextToken());
			res[weights[i]] = true;
		}
		
		// 구슬 무게 입력
		M = stoi(br.readLine());
		balls = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<M; i++) {
			balls[i] = stoi(st.nextToken());
		}
		
		// 구슬은, 왼쪽 추 무게 + 구슬 무게  == 오른쪽 추 무게 일 때 측정 가능하다.
		// => 구슬 무게 == Math.abs((왼쪽 추 무게) - (오른쪽 추 무게)) 일 때 구슬은 측정 가능하다.
		
		// 무게 확인
		// 1. 왼쪽 추 부터 선택한다.
		vl = new boolean[N];
		dfsLeft(0, 0, 0);
		
		for (int i = 0; i < M; i++) {
			if (res[balls[i]])
				sb.append("Y ");
			else
				sb.append("N ");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

	static void dfsLeft(int start, int totalLeft, int L) {
		if(L==N) {
			left = totalLeft;
			// 2. 오른쪽 무게 선택
			vr = new boolean[N];
			dfsRight(0, 0, 0);
			return;
		}
		
		for(int i=start; i<N; i++) {
			if(vl[i]) continue;
			// i번째 추 선택
			vl[i] = true;
			dfsLeft(i+1, totalLeft+weights[i], L+1);
			// i번째 추 선택x
			vl[i] = false;
			dfsLeft(i+1, totalLeft, L+1);
		}
	}
	
	static void dfsRight(int start, int totalRight, int L) {
		if(L==N) {
			right = totalRight;
			// 3. 왼쪽, 오른쪽 추가 모두 선택되었다면 양 측의 무게의 절대값 만큼 비교 가능하다.
			int t = Math.abs(right-left);
			res[t] = true;
			return;
		}
		
		for(int i=start; i<N; i++) {
			if(vl[i]) {
				// 이미 왼쪽에 선택된 추는 선택할 수 없다.
				vr[i] = true;
				dfsRight(i+1, totalRight, L+1);
			}
			
			if (vr[i]) continue;
			
			// i번째 추 선택
			vr[i] = true;
			dfsRight(i+1, totalRight+weights[i], L+1);
			// i번째 추 선택x
			vr[i] = false;
			dfsRight(i+1, totalRight, L+1);
		}
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
