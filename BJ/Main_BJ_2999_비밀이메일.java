import java.io.*;
import java.util.*;

// 210214

public class Main_BJ_2999_비밀이메일 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String input = sc.nextLine();
		int N = input.length();
		int r = 0, c = 0;
		
		for(int i=1; i<=N/2; i++) {
			if (N%i==0 && N/i>=i) {
				r = i;
				c = N/i;
			}
		}
		
		char[][] arr = new char[r][c];
		int idx = -1;
		for(int ci=0; ci<c; ci++) {
			for(int ri=0; ri<r; ri++) {
				arr[ri][ci] = input.charAt(++idx);
			}
		}
	
		StringBuilder sb = new StringBuilder();
		for(int ri=0; ri<r; ri++) {
			for(int ci=0; ci<c; ci++) {
				sb.append(arr[ri][ci]);
			}
		}
		System.out.println(sb.toString());
		
		sc.close();
	}

}
