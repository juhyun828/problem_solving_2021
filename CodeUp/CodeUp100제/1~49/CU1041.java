import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1041 : [기초-산술연산] 문자 1개 입력받아 다음 문자 출력하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char ch = br.readLine().charAt(0);
		ch +=1;
		System.out.println(ch);
	}
}
