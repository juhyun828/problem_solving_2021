import java.io.*;
import java.util.*;

// 210216
public class Main_BJ_13305_주유소 {
	
	static long stol(String str) {
		return Long.parseLong(str);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		long[] dist = new long[N-1];
		long[] price = new long[N-1];

		// 길이
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N-1; i++) dist[i] = stol(st.nextToken());
		
		// 가격
		 st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N-1; i++) price[i] = stol(st.nextToken());
		
		st.nextToken(); // 마지막 도시의 가격 필요 x
		
		Long total = price[0] * dist[0];
		
		for(int i=1; i<N-1; i++) {
			if (price[i-1] < price[i]) price[i] = price[i-1];
			// 뒤에 있는 도시의 가격이 더 비싸면, 앞에 도시에서 미리 구입한다.
		}
		
		for(int i=1; i<N-1; i++) {
			total += price[i] * dist[i];
		}
		
		System.out.println(total);
		br.close();
	}

}
