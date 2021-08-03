import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 210123
// 1086 : [기초-종합] 그림 파일 저장용량 계산하기

public class CU1086 {

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		long total = Long.parseLong(strArr[0]) * Long.parseLong(strArr[1]) * Long.parseLong(strArr[2]); // bit 단위
		
		double res = (float) total / 8; // byte 단위
		
		// bit -> byte -> KB -> MB
		res = res/1024/1024; 
		
		// 8000 8 1 60
		System.out.printf("%.2f MB", res);
		
		br.close();
	}
}