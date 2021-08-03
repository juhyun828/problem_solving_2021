import java.io.*;
import java.util.*;

// 210214
public class Main_BJ_2941_크로아티아알파벳 {
	static String[] two = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		int cnt = 0;
		char[] chArr = str.toCharArray();
		
		for(int i=0; i<chArr.length; i++) {

			if ((i+2)<chArr.length && chArr[i]=='d' && chArr[i+1]=='z' && chArr[i+2]=='=') {
				++cnt;
				chArr[i] = 0;
				chArr[i+1] = 0;
				chArr[i+2] = 0;
				i += 2;
			} else if ((i+1)<chArr.length && chArr[i]=='d' && chArr[i+1]=='-') {
				++cnt;
				chArr[i] = 0;
				chArr[i+1] = 0;
				i += 1;
			} else if ((i+1)<chArr.length && chArr[i]=='z' && chArr[i+1]=='=') {
				++cnt;
				chArr[i] = 0;
				chArr[i+1] = 0;
				i += 1;
			} else if ((i+1)<chArr.length && chArr[i]=='c' && chArr[i+1]=='=') {
				++cnt;
				chArr[i] = 0;
				chArr[i+1] = 0;
				i += 1;
			} else if ((i+1)<chArr.length && chArr[i]=='c' && chArr[i+1]=='-') {
				++cnt;
				chArr[i] = 0;
				chArr[i+1] = 0;
				i += 1;
			} else if ((i+1)<chArr.length && chArr[i]=='s' && chArr[i+1]=='=') {
				++cnt;
				chArr[i] = 0;
				chArr[i+1] = 0;
				i += 1;
			} else if ((i+1)<chArr.length && chArr[i]=='l' && chArr[i+1]=='j') {
				++cnt;
				chArr[i] = 0;
				chArr[i+1] = 0;
				i += 1;
			} else if ((i+1)<chArr.length && chArr[i]=='n' && chArr[i+1]=='j') {
				++cnt;
				chArr[i] = 0;
				chArr[i+1] = 0;
				i += 1;
			}

		}
		
		for(int i=0; i<chArr.length; i++) {
			if (chArr[i]!=0) ++cnt;
		}
		
		System.out.println(cnt);
		sc.close();
	} // 
}
