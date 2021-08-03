import java.io.*;
import java.util.*;
// 210311

public class Solution_모의_1052_수영장 {
	static int daily, month, threeMonth, year;
	static int min;
	static int[] plan;
	
	static void dfs(int total, int m) {
		if(m>=13) {
			// 12월 이후 중단, 일년권과 비교 후 min값 갱신
			int res = Math.min(total, year);
			min = Math.min(min, res);
			return;
		}
		
		int curMonth = Math.min(daily*plan[m], month);
		dfs(total+curMonth, m+1);
		
		if(m<=10) 
			dfs(total+threeMonth, m+3);
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_모의_1052_수영장.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			daily = stoi(st.nextToken()); // 1일권
			month = stoi(st.nextToken()); // 한달권
			threeMonth = stoi(st.nextToken()); // 세달치권
			year = stoi(st.nextToken()); // 일년권
			
			min = Integer.MAX_VALUE;
			plan = new int[13]; // 0-dummy, 1월부터 시작
			
			st = new StringTokenizer(br.readLine().trim());
			for(int i=1; i<=12; i++) {
				plan[i] = stoi(st.nextToken());
			}
			
			dfs(0, 1);
			sb.append("#" + tc + " " + min + "\n");	
		}
		System.out.println(sb.toString());
		
		br.close();
	} // 
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
