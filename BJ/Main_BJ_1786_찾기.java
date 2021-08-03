import java.io.*;
import java.util.*;
// 210322

public class Main_BJ_1786_찾기 {
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_BJ_1786_찾기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] T = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();
		int tl = T.length, pl = P.length;
		
		// 실패함수 만들기
		int[] fail = new int[pl];
		for(int i=1, j=0; i<pl; i++) { // i: 접미사, j: 접두사
			while(j>0 && P[i] != P[j]) {
				j = fail[j-1];
			}
			if(P[i] == P[j]) fail[i] = ++j;
		}
		
		int cnt=0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		// i: 텍스트 포인터, j: 패턴 포인터
		for(int i=0, j=0; i<tl; ++i) {
			while(j>0 && T[i]!=P[j]) j = fail[j-1];
			
			if(T[i] == P[j]) { // 두 글자 일치
				if(j==pl-1) { // j가 패턴의 마지막 인덱스라면
					cnt++;
					list.add((i+1)-pl+1);
					j = fail[j];
				} else {
					j++;
				}
			}	
		}
		System.out.println(cnt);
		if(cnt>0) {
			for(int i=0; i<cnt; i++) {
				System.out.println(list.get(i));
			}
		} 
		
		br.close();
	}

}
