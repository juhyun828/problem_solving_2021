import java.io.*;
import java.util.*;
// 210211

public class Main_BJ_2231_분해합 {
	
	static int N;
	
	static int func(int num) {
		
		String str = Integer.toString(num);
		int res = 0;
		
		for(int i=0; i<str.length(); i++){
			res += str.charAt(i) - '0';
		}
		
		return res+num;
		
 	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	
		int input = 1;
		
		while(input<N) {
			int output = func(input);
			
			if (output==N) break;
			else ++input;
		}
		
		// 분해합이 없는 경우 0을 출력한다.
		System.out.println((input==N) ? 0 : input);
		
		br.close();
	}
}
