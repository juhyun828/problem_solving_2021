import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CU1051 {

	public static void main(String[] args) throws IOException {
		// 1051 : [기초-비교연산] 두 정수 입력받아 비교하기3
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		int a = Integer.parseInt(strArr[0]);
		int b = Integer.parseInt(strArr[1]);
		
		System.out.println((a <= b) ? 1: 0);
	}
}

