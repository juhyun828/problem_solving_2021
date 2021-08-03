import java.io.*;
import java.util.*;
// 210412

public class Solution_모의_5644_무선충전 {
	static int M, bcCnt;
	static int ax, ay, bx, by;
	static int[] pathA, pathB;
	static Battery[] bc;
	
	static int[] dx = {0, 0, 1, 0, -1}; // x, 상, 우, 하, 좌
	static int[] dy = {0, -1, 0, 1, 0};
	
	static class Battery {
		int x, y, c, p;

		public Battery(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_모의_5644_무선충전.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = stoi(st.nextToken()); // 움직이는 초
			bcCnt = stoi(st.nextToken()); // 충전소 개수
			
			// 사용자 A의 위치
			ax = ay = 1;
			// 사용자 B의 위치
			bx = by = 10;
			
			pathA = new int[M+1];
			pathB = new int[M+1];
			// 사용자 A의 이동 정보
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<=M; i++)
				pathA[i] = stoi(st.nextToken());
			
			// 사용자 B의 이동정보
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<=M; i++)
				pathB[i] = stoi(st.nextToken());
			
			// 충전소 정보
			bc = new Battery[bcCnt];
			int x, y, c, p;
			for(int i=0; i<bcCnt; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x = stoi(st.nextToken());
				y = stoi(st.nextToken());
				c = stoi(st.nextToken());
				p = stoi(st.nextToken());
				bc[i] = new Battery(x, y, c, p);
			}
			
			sb.append("#" + tc + " " + move() + "\n");

		} // tc
		
		System.out.println(sb.toString());
		
	} // 
	
	static int move() {
		int totalSum = 0;
		//  매시간마다의 각 위치에서 두 플레이어의 최대 충전량을 계산하여 합산
		for(int t=0; t<=M; t++) {// 초기위치도 충전시도 
			ax += dx[pathA[t]];
			ay += dy[pathA[t]];
			bx += dx[pathB[t]];
			by += dy[pathB[t]];
			
			// 현 위치에서의 최대 충전량 계산
			totalSum += getMaxCharge();
		}
		return totalSum;
	}
	
	static int getPower(int bidx, int x, int y) {
		// bidx번째 bc와 (x. y) 간의 거리에 따른 충전량 계산
		// 범위 안에 (x, y)가 위치하지 않으면 충전량 0
		return Math.abs(bc[bidx].x-x) + Math.abs(bc[bidx].y-y) <= bc[bidx].c ? bc[bidx].p : 0;
	}

	static int getMaxCharge() {
		int max = 0;
		
		for(int a=0; a<bcCnt; a++) { // 플레이어 A가 선택한 BC
			for(int b=0; b<bcCnt; b++) { // 플레이어 B가 선택한 BC
				int sum = 0;
				// 해당 BC들을 선택했을 때 얻을 수 있는 충전량
				int amountA = getPower(a, ax, ay);
				int amountB = getPower(b, bx, by);		
			
				if(a!=b) {
					sum = amountA + amountB;
				}
				else {
					// 두 충전소가 같으면 -> 각자 충전량 반씩 택해서 더함
					// 둘 중 하나 택하는 것과 같음
					sum = Math.max(amountA, amountB);
				}
				
				if(max<sum) max = sum;
			}	
		}
		return max;
	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}