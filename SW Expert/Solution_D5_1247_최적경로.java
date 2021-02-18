import java.io.*;
import java.util.*;

// 210218

public class Solution_D5_1247_최적경로 {
	
	static int N; // 고객 수
	static int[][] input;
	static int[][] customer; // 고객 번호, 
	static int[] company;
	static int[] home;
	static int min;
	static boolean[] isSelected;
	
	static void permutation(int L) {
		
		if(L==N) {
			int res = dist(customer, 0, 0);
			if (res>-1) {
				min = Math.min(res, min);
			}
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			
			customer[L][0] = input[i][0];
			customer[L][1] = input[i][1];	
			isSelected[i] = true;
			permutation(L+1);
			isSelected[i] = false;
		}
	}
	
	static int dist(int[][] customer, int idx, int total) {
		if (total > min) return -1;
		
		if (idx==0) {
			int tmp = cal(company[0], company[1], customer[0][0], customer[0][1]);
			return dist(customer, idx+1, total+tmp); 
		}
		
		if (idx==N) {
			int tmp = cal(home[0], home[1], customer[N-1][0], customer[N-1][1]);
			return total+tmp;
		}
		
		int tmp = cal(customer[idx-1][0], customer[idx-1][1], customer[idx][0], customer[idx][1]);
		return dist(customer, idx+1, total+tmp); 
	}
	
	static int cal(int ar, int ac, int br, int bc) {
		return Math.abs(ar-br) + Math.abs(ac-bc);
	}
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_D5_1247_최적경로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			min = Integer.MAX_VALUE;
			N = stoi(br.readLine().trim());
			input = new int[N][2];
			company = new int[2]; home = new int[2];
			customer = new int[N][2];
			isSelected = new boolean[N];
			min = Integer.MAX_VALUE;
			
			StringTokenizer str = new StringTokenizer(br.readLine(), " ");
			company[0] = stoi(str.nextToken()); company[1] = stoi(str.nextToken());
			home[0] = stoi(str.nextToken()); home[1] = stoi(str.nextToken());
			
			for(int i=0; i<N; i++) {
				input[i][0] = stoi(str.nextToken());
				input[i][1] = stoi(str.nextToken());
				//System.out.println(Arrays.toString(input[i]));
			}
			
			permutation(0);
			System.out.println("#" + tc + " " + min);
			
		}
		br.close();
		
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
