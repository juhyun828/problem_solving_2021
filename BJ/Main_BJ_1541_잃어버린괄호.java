import java.io.*;
import java.util.*;

// 210216
public class Main_BJ_1541_잃어버린괄호 {
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		
		StringTokenizer st = new StringTokenizer(sc.nextLine(), "-");
		StringTokenizer st2 = null;
		
		int res = 0;
		boolean first = false;
		// +로 이루어진 부분을 먼저 계산한 후 빼주기
		while(st.hasMoreTokens()) {
			String s = st.nextToken();
			st2 = new StringTokenizer(s, "+");
			int sum = 0;
			while(st2.hasMoreTokens()) {
				sum += stoi(st2.nextToken());
			}
			if(!first) {
				res = sum;
				first = true;
			} else {
				res -= sum;
			}
		}
		System.out.println(res);
		
		sc.close();
	}
}
