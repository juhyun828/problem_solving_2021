import java.io.*;
import java.util.*;
// 210421

public class Solution_모의_2115_벌꿀채취 {
	static int N, M, C;
	static int[][] map;
	static int sr1, sc1, ec1, sr2, sc2, ec2;
	static int[] pickA, pickB;
	static int max;
	static int maxA, maxB;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_모의_2115_벌꿀채취.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
	
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			C = stoi(st.nextToken());
			sr1 = sc1 = sr2 = sc2 = ec1 = ec2 = 0;
			
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = stoi(st.nextToken());
				}
			}
			
			pickA  = new int[M];
			pickB = new int[M];
			maxA = 0; maxB = 0;
			max = 0;
			
			solve();
			sb.append("#" + tc + " " + max + "\n");
			
		} // tc
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void solve() {
		selectA();
		
	}
	
	static void selectA() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<=N-M; j++) {
				// A구역 택
				sr1 = i;
				sc1 = j;
				ec1 = j+M-1;
				
				selectB();
			}
		}
		
	}
	
	static void selectB() {
		
		// A랑 같은 행
		for(int j=ec1+1; j<=N-M; j++) {
			sr2 = sr1;
			sc2 = j;
			ec2 = j+M-1;
			// B구역 택
			pick();
		}
		
		// A 다음 행부터
		for(int i=sr1+1; i<N; i++) {
			for(int j=0; j<=N-M; j++) {
				sr2 = i;
				sc2 = j;
				ec2 = j+M-1;
				// B구역 택
				pick();
			}
		}
	}
	
	static void pick() {
		maxA = 0; maxB = 0;
		
		// A의 이익 최대
		subsetA(sr1, sc1, 0, 0);
		
		// B의 이익 최대
		subsetB(sr2, sc2, 0, 0);
		
		int total = maxA + maxB;
		max = Math.max(max, total);
	}
	
	static void subsetA(int sr, int sc, int cnt, int L) {
		if(L==M) {
			if(cnt==0) return;
			int profitA = 0;
			int totalA = 0;
			for(int i=0; i<M; i++) {
				if(pickA[i]==1) {
					totalA += map[sr][sc+i];
					profitA+=map[sr][sc+i]*map[sr][sc+i];
				}
				
			}
			if(totalA<=C) {
				maxA = Math.max(maxA, profitA);
			}
		
			return;
		}
		
		// L번째 꿀 선택
		pickA[L] = 1;
		subsetA(sr, sc, cnt+1, L+1);
		pickA[L] = 0;
		subsetA(sr, sc, cnt, L+1);
	}
	
	static void subsetB(int sr, int sc, int cnt, int L) {
		if(L==M) {
			if(cnt==0) return;
			int profitB = 0;
			int totalB = 0;
			for(int i=0; i<M; i++) {
				if(pickB[i]==1) {
					totalB += map[sr][sc+i];
					profitB+=map[sr][sc+i]*map[sr][sc+i];
				}
				
			}
			if(totalB<=C) {				
				maxB = Math.max(maxB, profitB);
			}
			
			return;
		}
		
		// L번째 꿀 선택
		pickB[L] = 1;
		subsetB(sr, sc, cnt+1, L+1);
		pickB[L] = 0;
		subsetB(sr, sc, cnt, L+1);
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
