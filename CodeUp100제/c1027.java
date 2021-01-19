import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1027 : [기초-입출력] 년월일 입력 받아 형식 바꿔 출력하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split("\\.");
		
		for (int i=2; i>=0; i--) {
			if (i == 0) {
				System.out.printf("%04d", Integer.parseInt(strArr[i]));
			} else {
				System.out.printf("%02d", Integer.parseInt(strArr[i]));
				System.out.print("-");
			}
		}
	}
}
