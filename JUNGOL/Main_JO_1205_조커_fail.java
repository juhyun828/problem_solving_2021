import java.io.*;
import java.util.*;
// 210225
// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=488&sca=99&sfl=wr_hit&stx=1205

public class Main_JO_1205_조커_fail {
	static ArrayList<Integer> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int joker = 0;
		list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			int tmp = sc.nextInt();
			if(tmp==0) ++joker;
			else if(!list.contains(tmp)) list.add(tmp); // 중복 제거
		}
		Collections.sort(list);
		System.out.println(list);
		int maxLen = joker;
		
		for(int i=0; i<list.size()-1; i++) {
			maxLen = Math.max(maxLen, findLen(i, i+1, joker));
		}
		
		System.out.println(maxLen);
		sc.close();
	}
	
	static int findLen(int i, int jj, int joker) {
		int len = 0;
		System.out.println("i " + i);
		for(int j=jj; j<list.size(); j++) {
			int diff = list.get(j) - list.get(i);
			if (diff==1) {
				len+=2;
			} else if((diff-1)<=joker) {
				joker-=(diff-1);
				len+=(diff+1);
			} else {
				break;
			}
			System.out.println("j " + j);
		}
		if(joker>0) len+=joker;
		System.out.println("i = " + i + ", len = " + len);
		return len;
	}
}
