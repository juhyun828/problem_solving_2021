import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1037 : [기초-출력변환] 정수 입력받아 아스키 문자로 출력하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int asc = Integer.parseInt(br.readLine());
		char ch = (char) asc;
		System.out.println(ch);
	}
}
