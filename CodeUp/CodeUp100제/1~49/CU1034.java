import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1034 : [기초-출력변환] 8진 정수 1개 입력받아 10진수로 출력하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine(), 8);
		System.out.println(num);
	}
}
