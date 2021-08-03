import java.io.*;
import java.util.*;
// 210323

public class Main_BJ_1463_1로만들기 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine().trim());
		int d[] = new int[N+1];
		d[1] = 0;
//		if(N>=3) d[3] = 1;
//		if(N>=2) d[2] = 1;
		
		for(int n=2; n<=N; n++) {
			d[n] = d[n-1]+1;
			if(n%2==0) d[n] = Math.min(d[n], d[n/2] + 1);
			if(n%3==0) d[n] = Math.min(d[n], d[n/3] + 1);
		}
		
		System.out.println(d[N]);
		
		br.close();
	}
}
