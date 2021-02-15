import java.io.*;
import java.util.*;

// 210214

public class Solution_D3_6806_규영이와인영이의카드게임 {
	static int[] yourSelectedCards;
	static boolean[] isSelected;
	static int[] myCards;
	static int[] yourCards;
	static int myPoint, yourPoint, win, lose;
	
	static void check() {
		myPoint = 0;
		yourPoint = 0;
		
		for(int i=0; i<9; i++) {
			if (myCards[i]>yourSelectedCards[i]) {
				myPoint += myCards[i];
				myPoint += yourSelectedCards[i];
			} else if (myCards[i]<yourSelectedCards[i]) {
				yourPoint += myCards[i];
				yourPoint += yourSelectedCards[i];
			}
		}

		if (myPoint>yourPoint) {
			++win;
		}
		else if (myPoint<yourPoint) ++lose;
	}
	
	static void permutation(int cnt) {
		if (cnt == 9) {
			check();
			return; 
		}
		
		for(int i=0; i<9; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			yourSelectedCards[cnt]=yourCards[i];
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
	
//	static void permutation2 (int cnt, int flag) {
//		if (cnt == N) {
//			totalCount++;
//			System.out.println(Arrays.toString(numbers));
//			return;
//		}
//		for(int i=0; i<N; i++) {
//			if((flag)&1 << i) != 0) continue;
//			numbers[cnt] = 
//		}
//	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_D3_6806_규영이와인영이의카드게임.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			yourSelectedCards = new int[9];
			myCards = new int[9];
			yourCards = new int[9];
			isSelected = new boolean[9];
			myPoint = 0;
			yourPoint = 0;
			win = 0;
			lose = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] cards = new int[19]; // 1~18번지까지 사용
			for(int i=0; i<9; i++) {
				myCards[i] = Integer.parseInt(st.nextToken());
				cards[myCards[i]] = 1;
			}
			
			int idx = -1;
			for(int i=1; i<=18; i++) {
				if (cards[i] == 0) {
					yourCards[++idx] = i;
				}
			}
			permutation(0);
			System.out.println("#" + tc + " " + win + " " + lose);
		}
		
		br.close();
	}

}
