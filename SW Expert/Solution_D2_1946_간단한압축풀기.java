import java.io.BufferedReader;;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D2_1946_간단한압축풀기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				String alp = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				for(int j=0; j<num; j++) {
					sb.append(alp);
				}
			}
			
			System.out.println("#" + tc);
			for(int i=1; i<=sb.length(); i++) {
				System.out.print(sb.charAt(i-1));
				if(i%10==0) System.out.println();				
			}
			System.out.println();
		}
		br.close();;
	} // main

}