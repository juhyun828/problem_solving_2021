import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1026 : [기초-입출력] 시분초 입력받아 분만 출력하기
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(":");
		System.out.println(Integer.parseInt(strArr[1]));
		
	}
}
