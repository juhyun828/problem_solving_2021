import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1039 : [기초-산술연산] 정수 2개 입력받아 합 출력하기2
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		long a = Long.parseLong(strArr[0]);
		long b = Long.parseLong(strArr[1]);
		
		System.out.println(a+b);
		
	}
}
