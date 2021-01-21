import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1045 : [기초-산술연산] 정수 2개 입력받아 자동 계산하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		long a = Long.parseLong(strArr[0]);
		long b = Long.parseLong(strArr[1]);
		
		System.out.println(a + b);
		System.out.println(a - b);
		System.out.println(a * b);
		System.out.println(a / b);
		System.out.println(a % b);
		System.out.printf("%.2f", (float)a/b);
	}
}
