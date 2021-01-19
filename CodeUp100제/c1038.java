import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1038 : [기초-산술연산] 정수 2개 입력받아 합 출력하기1
		// 입력되는 정수는 -1073741824 ~ 1073741824 이다
		// int형의 범위는 -2^31 ~ 2^31-1 (-2147483648 ~ 2147483647)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		long a = Long.parseLong(strArr[0]);
		long b = Long.parseLong(strArr[1]);
		
		System.out.println(a+b);
		
	}
}
