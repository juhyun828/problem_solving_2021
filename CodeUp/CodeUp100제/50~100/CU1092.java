import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 210123
// 1092 : [기초-종합] 함께 문제 푸는 날

public class CU1092 {

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		
		int a = Integer.parseInt(strArr[0]);
		int b = Integer.parseInt(strArr[1]); 
		int c = Integer.parseInt(strArr[2]); 
		
		int day = 1;
		while (day%a!= 0 || day%b!= 0 || day%c!= 0) day++;
		
		System.out.println(day);
		
		br.close();
	}
}