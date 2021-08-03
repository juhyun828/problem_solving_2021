import java.io.*;
import java.util.*;
// 210316

public class Solution_모의_4008_숫자만들기 {
	static int N, min, max;
	static int[] nums;
	static int[] op;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void cal(int idx, int res, int plus, int minus, int mul, int div) {
		if(idx==N) {
			min = Math.min(min, res);
			max = Math.max(max, res);
			return; 
		}
		
		if(plus>0) {
			cal(idx+1, res+nums[idx], plus-1, minus, mul, div);
		}
		
		if (minus>0) {
			cal(idx+1, res-nums[idx], plus, minus-1, mul, div);
		}
		
		if (mul>0) {
			cal(idx+1, res*nums[idx], plus, minus, mul-1, div);
		}
		
		if (div>0) {
			cal(idx+1, res/nums[idx], plus, minus, mul, div-1);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_모의_4008_숫자만들기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = stoi(br.readLine());
			op = new int[4]; // 연산 개수 , + - * / 순
			nums = new int[N]; 
			min = Integer.MAX_VALUE; max = Integer.MIN_VALUE;
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<4; i++) {
				op[i] = stoi(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " "); // 수식에 사용되는 숫자
			for(int i=0; i<N; i++) {
				nums[i] = stoi(st.nextToken());
			}
			
			cal(1, nums[0], op[0], op[1], op[2], op[3]); 
			// nums[0]은 이미 더해주고 시작했기 때문에 idx=1부터 시작!!
			sb.append("#" + tc + " " + (max - min) + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	} // 
}
