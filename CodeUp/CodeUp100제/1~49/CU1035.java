import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1035 : [기초-출력변환] 16진 정수 1개 입력받아 8진수로 출력하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine(), 16);
		System.out.printf("%o", num);
	}
}
