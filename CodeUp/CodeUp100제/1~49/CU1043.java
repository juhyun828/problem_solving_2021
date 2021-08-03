import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1043 : [기초-산술연산] 정수 2개 입력받아 나눈 나머지 출력하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		int a = Integer.parseInt(strArr[0]);
		int b = Integer.parseInt(strArr[1]);
		
		System.out.println(a%b);
	}
}
