import java.io.*;
import java.util.*;
// 210315

public class Solution_D4_8382_방향전환 {
	static int min;
	static int[] start, end;
	static int startX, startY, endX, endY;

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void go(int x, int y, boolean flag, int cnt) { // flag=true 가로, flag=false 세로
		
		if(x==endX&&y==endY) {
			min = Math.min(min, cnt);
			return;
		}
		
		if(flag) { // 가로로 가라
			if(x < endX) { // 우 방향
				go(x+1, y, false, cnt+1); // 다음 방향은 세로
			} else { // 좌 방향
				go(x-1, y, false, cnt+1); // 다음 방향은 세로
			}
		} else { // 세로로 가라
			if(y < endY) {
				go(x, y+1, true, cnt+1); // 위로
			} else {
				go(x, y-1, true, cnt+1); // 아래로
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_D4_8382_방향전환.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			st = new StringTokenizer(br.readLine().trim(), " ");
			min = Integer.MAX_VALUE;
			
			startX = stoi(st.nextToken()); // x
			startY = stoi(st.nextToken()); // y
			endX = stoi(st.nextToken()); // x
			endY = stoi(st.nextToken()); // y
			
			go(startX, startY, true, 0);
			go(startX, startY, false, 0);
			sb.append("#" + tc + " " + min + "\n");
		}
		System.out.println(sb.toString());
		
		br.close();
	} //
}

