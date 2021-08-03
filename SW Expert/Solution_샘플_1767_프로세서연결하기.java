import java.io.*;
import java.util.*;

// 210301

public class Solution_샘플_1767_프로세서연결하기 {
	
	static int N, max, totalCnt, min, map[][];
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<int[]> list; // 고려할 코어만 담는 리스트 (가장자리 코어 배제)
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}

	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("res/input_D_1767_프로세서연결하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = stoi(br.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>();
			max = 0; // 최대로 구할 수 있는 코어 개수
			min = Integer.MAX_VALUE; // 코어 개수가 같다면 전선의 개수가 최소일 때
			totalCnt = 0; // 총 관리해야 할 코어 개수
			
			for(int r=0; r<N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int c=0; c<N; c++) {
					map[r][c] = stoi(st.nextToken());
					if(r==0 || c==0 || r==N-1 || c==N-1) continue;
						// 가장자리 무시
					if(map[r][c]==1) {
						list.add(new int[] {r, c});
						++totalCnt;
					}

				}
			}
			go(0, 0);
			System.out.println("#" + tc + " " + min);

		}

		br.close();
	} // main

	static void go(int index, int cCnt) { 	// 선택/비선택 - 부분집합의 논리
		// index : 부분집합에 고려할 코어 인덱스, cCnt : 연결된 코어 개수		
		
		// 남은 코어 수(totalCnt-index) + 현재까지 지나온 코어 수(cCnt) < max 
		// => 앞으로 남은 코어를 다 선택해도 max보다 작음
		// 가지치기
		if(totalCnt-index+cCnt<max) return;
		
		if(index == totalCnt) { // 마지막 코어까지 다 따져봄
			int res = getLength(); // 놓아진 전선의 길이 구하기
			
			if (max<cCnt) {
				max = cCnt; // 코어 개수 갱신
				min = res; // 전선 길이 갱신
			} else if(max==cCnt) {
				if(res<min) min=res;
			}
			return; // 밑에 수행 x
		}
		
		// 코어 선택 전선 놓아보기 (4방향으로 놓아보기)
		int[] cur = list.get(index);
		int r = cur[0]; int c = cur[1]; 
		for(int d=0; d<4; d++) {
			// 현재 위치를 기준으로 끝까지 가보는 시도, 중간에 장애물 만나면 멈춤
			if(isAvailable(r, c, d)) {
				// 전선 놓기
				setStatus(r, c, d, 2); // 2 : 전선놓기
				go(index+1, cCnt+1); // 다음 코어 넘어가기
				// 놓았던 전선 되돌려놓기
				setStatus(r, c, d, 0); // 0 : 원래 상태
				// 어차피 장애물이 없는 위치와 방향에서 2를 놓았기 때문에, 
				// 장애물 검사 없이 0으로 돌려놓기만 하면 됨
			}
		}
		
		// 코어 비선택
		go(index+1,cCnt); 
	}
	
	static void setStatus(int r, int c, int d, int s) { // 상태 s로 놓기
		int nr = r, nc = c;
		while(true) {
			nr += dr[d]; 
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break;
			map[nr][nc] = s;
		}
		
	}
	
	static boolean isAvailable(int r, int c, int d) {
		int nr = r, nc = c;
		while(true) {
			nr += dr[d]; 
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break;
			// 0 빈칸, 1 코어, 2 전선
			if(map[nr][nc]>=1) return false; // 코어나 전선이 놓아져있어 불가능 
		}
		return true;
	}
	
	static int getLength() {
		int lCnt = 0; // 전선이 놓아진 애들은 2
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c]==2) lCnt++;
			}
		}
		return lCnt;
	}
	
}
