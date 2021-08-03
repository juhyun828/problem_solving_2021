import java.io.*;
import java.util.*;

//210210 

public class Main_BJ_17406_배열돌리기4 {
	
	static int N, M, K;
	static int[][] arr;
	static int[][] commands;

	static int[] dr = {1, 0, -1, 0};	// 시계방향
	static int[] dc = {0, 1, 0, -1};
	static int minArrPoint;
	static boolean[] isSelected;
	static int[] commandNumbers;
 	
	static void printArr(int[][] curArr) {
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=M; c++) {
				System.out.print(curArr[r][c] + " ");
			}
			System.out.println();
		}
	} 
	
	static int[][] rotate(int r, int c, int s, int[][] curArr) {
		// s개의 sub 네모
		for(int si=0; si<s; si++) {
			int sr = r-s + si; // 시작점
			int sc = c-s + si;
			
			int startR = r-s + si;
			int startC = c-s + si;
			int endR = r+s - si; // 끝나는 좌표
			int endC = c+s - si;
			
			int startValue = curArr[sr][sc];
			int d = 0;
			
			while(d < 4) {
				int nr = sr + dr[d];
				int nc = sc + dc[d];
				
				if(nr>=startR && nr<=endR && nc>=startC && nc<=endC) {						
					curArr[sr][sc] = curArr[nr][nc];
					sr = nr;
					sc = nc;
				} else {
					d++;
				}
			
			} // 
			curArr[startR][startC+1] = startValue;
		}
		return curArr;
	} // rotate
	 
	static int calculate(int[][] curArr) {
		// 각 행에 있는 모든 수의 합 중 최소값
		int minCur = Integer.MAX_VALUE;
		for (int r=1; r<=N; r++) {
			int sum = 0;
			for (int c=1; c<=M; c++) {
				sum += curArr[r][c];
			}
			minCur = Math.min(minCur, sum);
		}
		return minCur;
	}
	
	static void permutation(int cnt) {
		if (cnt==K) {
			int[][] cloneArr = new int[N+1][M+1];
			// 원본 배열 자체를 rotate 하면, 다음 순열 순서로 rotate 할 때 바뀐 배열로 계산하게 된다.
			for(int r=1; r<=N; r++) {
				for(int c=1; c<=M; c++) {
					cloneArr[r][c] = arr[r][c];
				}
			}

			for(int i=0; i<K; i++) {
				int curCommands = commandNumbers[i];
				cloneArr = rotate(commands[curCommands][0], commands[curCommands][1], 
						commands[curCommands][2], cloneArr);
			}

			int tmpPoint = calculate(cloneArr);
			minArrPoint = Math.min(minArrPoint, tmpPoint);
			return;
		}
		
		for(int i=0; i<K; i++) {
			if (isSelected[i]) continue;
			
			isSelected[i] = true;
			commandNumbers[cnt] = i;
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		minArrPoint = Integer.MAX_VALUE;
		
		arr = new int[N+1][M+1];
		
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=1; c<=M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());;
			}
		}
		
		commands = new int[K][3];
		for(int ki=0; ki<K; ki++) {
			st = new StringTokenizer(br.readLine(), " ");
			commands[ki][0] = Integer.parseInt(st.nextToken());
			commands[ki][1] = Integer.parseInt(st.nextToken());
			commands[ki][2] = Integer.parseInt(st.nextToken());
		} 
	
		isSelected = new boolean[K]; 
		commandNumbers = new int[K];
		permutation(0);
		
		System.out.println(minArrPoint);
 		br.close();
	}
}
