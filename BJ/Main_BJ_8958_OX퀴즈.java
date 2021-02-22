import java.io.*;
import java.util.*;
// 210222

public class Main_BJ_8958_OX퀴즈 {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();

		for(int tc=1; tc<=N; tc++) {
			String str = sc.next();
			int cnt=0, ans=0;
			for(int i=0; i<str.length(); i++) {
				if (str.charAt(i)=='O') ++cnt;
				else if(str.charAt(i)=='X') cnt=0;
				ans += cnt;
			}
			System.out.println(ans);
		}

		sc.close();
	}

}
