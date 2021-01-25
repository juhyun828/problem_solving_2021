import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1040 : [기초-산술연산] 정수 1개 입력받아 부호 바꿔 출력하기
		// 단항 연산자인 -(negative)를 변수 앞에 붙이면 부호가 반대로 바뀌어 계산된다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = -Integer.parseInt(br.readLine());
		System.out.println(num);
		
	}
}
