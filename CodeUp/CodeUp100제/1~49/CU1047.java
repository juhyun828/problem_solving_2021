import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1047 : [기초-비트시프트연산] 정수 1개 입력받아 2배 곱해 출력하기
/*		- 왼쪽 비트시프트(<<)가 될 때에는 오른쪽에 0이 주어진 개수만큼 추가되고,
		- 오른쪽 비트시프트(>>)가 될 때에는
		 왼쪽에 0(0 또는 양의 정수인 경우)이나 1(음의 정수인 경우)이 개수만큼 추가된다.
		 범위(32비트)를 넘어서 이동되는 비트는 삭제된다.*/

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		System.out.printf("%d", num << 1);
		
	}
}
