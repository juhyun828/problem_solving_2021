import java.io.*;
import java.util.*;
// 210316

public class Main_BJ_1759_암호만들기 {
	static int L, C;
	static char[] input;
	static char[] cRes;
	static StringBuilder sb = new StringBuilder();
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void combination(int cnt, int level) {
		if(cnt==L) {
			if(check(cRes)) {
				for(char c: cRes) {
					sb.append(c);
				}
				sb.append("\n");
			}
			return;
		}
		
		if(level==C) return;
		
		// 선택 o
		cRes[cnt] = input[level];
		combination(cnt+1, level+1);
		// 선택 x
		combination(cnt, level+1);
	}
	
	static boolean check(char[] password) {
		int ja=0, mo=0;
		for(char c: password) {
			if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				++mo;
			} else {
				++ja;
			}
			if(mo>=1 && ja>=2) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_1759_암호만들기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		
		L = stoi(st.nextToken());
		C = stoi(st.nextToken());
		input = new char[C];
		cRes = new char[L];
		
		st = new StringTokenizer(br.readLine().trim(), " ");
		for(int i=0; i<C; i++) {
			input[i] = st.nextToken().charAt(0);	
		}
		
		Arrays.sort(input);
		combination(0, 0);
		System.out.println(sb.toString());
		br.close();
	}
}
