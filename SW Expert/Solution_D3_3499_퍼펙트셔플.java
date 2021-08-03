import java.io.*;
import java.util.*;

// 210205 

public class Solution_D3_3499_퍼펙트셔플 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] strArr = br.readLine().split(" ");
			StringBuilder sb = new StringBuilder();
			sb.append("#" + tc + " ");
			
			int mid = N/2;

			if (N%2==1) {
				for (int i=0; i<mid; i++) {
					sb.append(strArr[i] + " " + strArr[i+mid+1] + " ");
				}
				sb.append(strArr[mid]);
			} else {
				for (int i=0; i<mid; i++) {
					sb.append(strArr[i] + " " + strArr[i+mid] + " ");
				}
			}

			System.out.println(sb.toString());
			
		} // for
		
		br.close();
	}
}
