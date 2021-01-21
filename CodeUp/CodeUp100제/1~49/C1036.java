import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1036 : [기초-출력변환] 영문자 1개 입력받아 10진수로 출력하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int asc = br.readLine().charAt(0);
		System.out.println(asc);
	}
}
