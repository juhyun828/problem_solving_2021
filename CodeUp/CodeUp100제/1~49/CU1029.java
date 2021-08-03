import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1029 : [기초-데이터형] 실수 1개 입력받아 그대로 출력하기2
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double num = Double.parseDouble(br.readLine()); 
		System.out.printf("%.11f", num); // %.11lf 를 사용하면 소수점 이하 11자리까지 출력된다.
	}
}
