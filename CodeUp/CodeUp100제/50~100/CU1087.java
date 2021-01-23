import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 210123
// 1087 : [기초-종합] 여기까지! 이제 그만~

public class CU1087 {

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int inputNum = Integer.parseInt(br.readLine()); 
		
		int num = 1;
		int sum = 0;
		
		do {
			sum += num++;
		} while(sum < inputNum);
		
		System.out.println(sum);
		
		br.close();
	}
}