import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1028 : [기초-데이터형] 정수 1개 입력받아 그대로 출력하기2
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long num = Long.parseLong(br.readLine()); //입력되는 정수의 범위는 0 ~ 4,294,967,295 이기 때문에 long을 사용한다.
		System.out.println(num);
	}
}
