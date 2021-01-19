import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1046 : [기초-산술연산] 정수 3개 입력받아 합과 평균 출력하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		long a = Long.parseLong(strArr[0]);
		long b = Long.parseLong(strArr[1]);
		long c = Long.parseLong(strArr[2]);
		
		System.out.println(a + b + c);
		System.out.printf("%.1f", (float)(a + b + c)/3);
	}
}
