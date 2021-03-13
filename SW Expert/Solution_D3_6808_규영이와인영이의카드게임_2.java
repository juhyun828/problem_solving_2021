import java.io.*;
import java.util.*;
// 210314

public class Solution_D3_6808_규영이와인영이의카드게임_2 {
	static boolean[] visited, inputFlag;
	static ArrayList<Integer> gyuCard, inCard;
	static int[] inRes;
	static int win, lose;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void permutation(int L) {
		if(L==9) {
			startGame();
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			inRes[L] = inCard.get(i);
			permutation(L+1);
			visited[i] = false;
		}
	}
	
	static void startGame() {
		int gyuPoint=0, inPoint=0, sum;
		
		for(int i=0; i<9; i++) {
			sum = gyuCard.get(i) + inRes[i];
			if(gyuCard.get(i) > inRes[i]) gyuPoint += sum;
			else if (gyuCard.get(i) < inRes[i]) inPoint += sum;
		}
		if(gyuPoint>inPoint) ++win;
		else if(gyuPoint<inPoint) ++lose;
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_D3_6808_규영이와인영이의카드게임.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			visited = new boolean[9]; // permutation용
			inputFlag = new boolean[19]; // 규영이가 선택한 카드 input용
			gyuCard = new ArrayList<>(); inCard = new ArrayList<>();
			win=0; lose = 0;
			inRes = new int[9];
			
			st = new StringTokenizer(br.readLine(), " ");
			// 규영이 카드 순서 고정
			for(int i=1; i<=9; i++) {
				int g = stoi(st.nextToken());
				gyuCard.add(g);
				inputFlag[g] = true;
			}
			// 인영이 카드
			for(int i=1; i<=18; i++) {
				if(!inputFlag[i]) inCard.add(i);
			}

			permutation(0);
			sb.append("#" + tc + " " + win + " " + lose + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
