import java.io.*;
import java.util.*;
// 210209

public class Solution_D4_1233_사칙연산유효성검사 {
	
	// 1. 연산자는 두개의 숫자 자식 노드를 가져야 한다.
	// 1-1. 연산자는 리프 노드에 올 수 없다.
	// 2. 숫자는 자식 노드를 가질 수 없다.

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine());
			String opers = "+-*/";
			
			StringTokenizer st;
			
			int res = 1;
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				if (st.countTokens() == 4) {
					st.nextToken();
					String st1 = st.nextToken();
					if(opers.contains(st1)) {
						// 다음으로 오는 자식 노드 두개가 모두 숫자여야 함
						String st3 = st.nextToken();
						String st4 = st.nextToken();
						if (opers.contains(st3) || opers.contains(st4)) {
							 // 한번이라도 잘못된 입력값이 들어오면 res는 0이 된다.		
							res = 0;
						}
					} else {
						// 길이가 4인데 부모노드로 연산자가 들어오지 않아도 잘못된 경우
						res = 0;
					}
				} else { // 길이가 2일 때
					st.nextToken();
					String st1 = st.nextToken();
					if(opers.contains(st1)) {
						 // 한번이라도 잘못된 입력값이 들어오면 res는 0이 된다.		
						res = 0;
					}
				}

			} // for
			System.out.println("#" + tc + " " + res);
			
		} // for
		
		br.close();
	}

}
