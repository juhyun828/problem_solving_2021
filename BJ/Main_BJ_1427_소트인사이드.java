import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
// 210328

public class Main_BJ_1427_소트인사이드 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] chArr = br.readLine().toCharArray();
		int L = chArr.length;
		Integer[] intArr = new Integer[L];
		
		for(int i=0; i<L; i++) {
			intArr[i] = chArr[i] - '0';
		}
		
		Arrays.sort(intArr, Collections.reverseOrder());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<L; i++) {
			sb.append(intArr[i]);
		}
		System.out.println(sb.toString());
				
		br.close();
	}
}
