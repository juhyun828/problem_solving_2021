import java.io.*;
import java.util.*;
// 210220

public class Solution_D3_4789_성공적인공연기획 {
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_D3_4789_성공적인공연기획.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			
			String[] s = sc.next().split("");
			int cnt=stoi(s[0]), ans=0;
			
			if(s.length>0) {
				for(int i=1; i<s.length; i++) {
					if (i<=cnt) {
						cnt += stoi(s[i]);
					} else {
						int diff = i - cnt;
						ans += diff;
						cnt += diff;
						cnt += stoi(s[i]);
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		} // tc
		
		sc.close();
	}
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
