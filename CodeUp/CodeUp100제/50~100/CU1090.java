import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 210123
// 1090 : [기초-종합] 수 나열하기2

public class CU1090 {

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		
		int a = Integer.parseInt(strArr[0]);
		int d = Integer.parseInt(strArr[1]);
		int n = Integer.parseInt(strArr[2]);
		
		long res = a; // 1st
		for (int i=2; i<=n; i++) { // 2nd ~ nst
			res *= d;
		}
		
		// int는 최대 2147483647까지 표현 가능
		//            2147000000(열자리) 언저리라고 기억하기
		System.out.println(res);
        System.out.println(a * (long)Math.pow(d, n-1));
		br.close();
	}
}