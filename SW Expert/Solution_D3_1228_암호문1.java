import java.io.*;
import java.util.*;

// 210208 
public class Solution_D3_1228_암호문1 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		
		for (int tc=1; tc<=T; tc++) {
			
			StringBuilder sb = new StringBuilder();
			sb.append("#" + tc);
			
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			LinkedList<String> list = new LinkedList<String>();
			list.add("head"); // 0번째

			for (int i=0; i<N; i++) {
				list.add(st.nextToken());
			}

			int M = Integer.parseInt(br.readLine()); // 명령어의 개수
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int i=1; i<=M; i++) {
				String insert = st.nextToken(); // I 명령어
				int x = Integer.parseInt(st.nextToken()); // 암호문이 들어갈 위치
				int y = Integer.parseInt(st.nextToken()); // 추가될 암호문 길이
				
				int idx = x+1;
				for (int j=0; j<y; j++) {
					list.add(idx++, st.nextToken());
				}
			}
			
			// 출력
			for(int i=1; i<=10; i++) {
				sb.append(" " + list.get(i));
			}
	
			System.out.println(sb.toString());
		}

		br.close();
	}

}
