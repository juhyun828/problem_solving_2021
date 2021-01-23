import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 210123
// 1091 : [기초-종합] 수 나열하기3

public class CU1091 {

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		
		int a = Integer.parseInt(strArr[0]);
		int m = Integer.parseInt(strArr[1]); // 곱할 값
		int d = Integer.parseInt(strArr[2]); // 더할 값
		int n = Integer.parseInt(strArr[3]);
		
		long res = a; // 1st
		for (int i=2; i<=n; i++) { // 2nd ~ nst
			res = res * m + d;
		}
		
		System.out.println(res);
		br.close();
	}
}