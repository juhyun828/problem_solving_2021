import java.io.*;
import java.util.*;

// 210209

public class Main_BJ_2577_숫자의개수 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		
		// String str = String.format("%d", a*b*c);
		String str = Integer.valueOf(a*b*c).toString();	
		int[] intArr = new int[10];
	
		for(int i=0; i<str.length(); i++) {
			intArr[str.charAt(i)-'0']++;
		}
		
		for (int i : intArr) {
			sb.append(i + "\n");
		}
		System.out.println(sb.toString());
		
		br.close();
	} // main

}
