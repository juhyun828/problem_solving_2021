import java.io.*;
import java.util.*;

public class Main_BJ_1755_숫자놀이 {
	static int M, N;
	static Data[] arr;
	static String[] strArr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	
	static class Data implements Comparable<Data>{
		int origin;
		String str;
		
		public Data(int origin, String str) {
			this.origin = origin;
			this.str = str;
		}
		
		@Override
		public int compareTo(Data o) {
			// 문자열을 사전순으로 정렬한다.
			return (this.str).compareTo(o.str);
		}
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		M = stoi(st.nextToken());
		N = stoi(st.nextToken());
		arr = new Data[N-M+1];
		
		int idx=-1;
		char[] chArr; 
		StringBuffer sbf = new StringBuffer();
		for(int i=M; i<=N; i++) { // M 이상 N 이하의 수들 확인
			sbf = new StringBuffer();
			chArr = Integer.toString(i).toCharArray();
			for(char c: chArr) {
				sbf.append(strArr[c-'0'] + " ");
			} 
			arr[++idx] = new Data(i, sbf.toString());
		}
		
		// 사전순 정렬
		Arrays.sort(arr);
		
		// 한줄에 10개씩 출력한다.
		for(int i=0; i<arr.length; i++) {
			sb.append(arr[i].origin + " ");
			if(i%10==9) sb.append("\n");
		}
		System.out.println(sb.toString());

		br.close();
	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}