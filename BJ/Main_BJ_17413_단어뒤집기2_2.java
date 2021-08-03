import java.io.*;
import java.util.*;
// 210226
// https://www.acmicpc.net/problem/17413

public class Main_BJ_17413_단어뒤집기2_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		sb.append(sc.nextLine());
		
		for(int i=0; i<sb.length(); i++) {
			if(sb.charAt(i)==' ') continue;
			
			if(sb.charAt(i)=='<') {
				for(int j=i+1; j<sb.length(); j++) {
					if(sb.charAt(j)=='>') {
						i=j; break;
					}
				}
			} else { // 문자는 뒤집는다.
				for(int j=i+1; j<sb.length(); j++) {
					if(sb.charAt(j)=='<' || sb.charAt(j)==' ' || j==sb.length()-1) {
						if(j==sb.length()-1) ++j;
						StringBuilder origin = new StringBuilder();
						origin.append(sb.substring(i, j));
						origin.reverse();
						sb.replace(i, j, origin.toString());
						i=(j-1); break; 
						// substring(i, j)과 replace(i, j, str) 모두 i<= x <j인 범위에서 메소드를 수행하지만
						// for을 돌 때 마다 i++되기 때문에, i가 두번 커지지 않도록 i를 j-1로 만들어야 한다.
						// 즉, i의 위치를 검사할 다음 위치 이전으로 만든다.
					}
				}
			}
		} // for
		
		System.out.println(sb.toString());
		
		sc.close();
	} // 
}
