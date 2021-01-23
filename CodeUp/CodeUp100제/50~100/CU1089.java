import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 210123
// 1089 : [기초-종합] 수 나열하기1

public class CU1089 {

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		
		int a = Integer.parseInt(strArr[0]);
		int d = Integer.parseInt(strArr[1]);
		int n = Integer.parseInt(strArr[2]);
		
		
		// 첫번째 수는 이미 a이기 때문에 n-1을 곱해야 함
		System.out.println(a + d*(n-1));
		
		br.close();
	}
}