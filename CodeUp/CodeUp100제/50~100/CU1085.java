import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CU1085 {

	public static void main (String[] args) throws IOException {
		// h, b, c, s
		// h는 48,000이하, b는 32이하(단, 8의배수), c는 5이하, s는 6,000이하의 자연수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		long total = Long.parseLong(strArr[0]) * Long.parseLong(strArr[1]) * Long.parseLong(strArr[2]) * Long.parseLong(strArr[3]);
		
		double res = (float) total / 8; // byte 단위
		
		// bit -> byte -> KB -> MB
		res = res/1024/1024; 
		
		// 8000 8 1 60
		System.out.printf("%.1f MB", res);
		
		br.close();
	}
}