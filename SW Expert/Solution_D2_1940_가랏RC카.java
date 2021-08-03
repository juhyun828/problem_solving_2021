import java.io.*;
import java.util.*;

// 210208 

public class Solution_D2_1940_가랏RC카 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			int v = 0;
			int s = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int n1 = Integer.parseInt(st.nextToken());
				
				if (n1==0) {
					s += v;
				} else if (n1==1) {
					// 가속 
					int n2 = Integer.parseInt(st.nextToken());
					v += n2;
					s += v;
				} else if (n1==2) {
					// 감속 
					int n2 = Integer.parseInt(st.nextToken());
					v -= n2;
					if (v<0) v=0;
					s += v;
					
				}
				
			} // for
			
			System.out.println("#" + tc + " " + s);
			
		}
		
		br.close();
	}

}
