import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 210123
// 1088 : [기초-종합] 3의 배수는 통과?

public class CU1088 {

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int inputNum = Integer.parseInt(br.readLine()); 
		
		for (int i=1; i<=inputNum; i++) {
			if (i % 3 == 0) continue;
			System.out.print(i + " ");
		}
		
		br.close();
	}
}