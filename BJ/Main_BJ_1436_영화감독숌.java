import java.io.*;
import java.util.*;
// 210211

public class Main_BJ_1436_영화감독숌 {
	static int N;
	static int[] numbers;
	static int pos;
	static boolean[] isSelected;
	static HashSet<Integer> hashList = new HashSet<Integer>();
	
	static void permutation(int cnt, int L, int D) {
		if (cnt == 1) {
			String[] tmpTitle = new String[D];
			tmpTitle[pos] = "666";
			fullTitle(tmpTitle, D, 0);
			return;
		}
		
		if (L >= D) return;
		
		for(int i=0; i<D; i++) {
			if (isSelected[i]) continue;
			
			pos = i;
			isSelected[i] = true;
			permutation(cnt+1, L+1, D);
			isSelected[i] = false;
		}
	}
	
	static void fullTitle(String[] tmpTitle, int D, int cnt) {
		String[] title = tmpTitle;
		
		if (cnt == D) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<title.length; i++) {
				sb.append(title[i]);
			}
			hashList.add(Integer.parseInt(sb.toString()));
			return;
		}
		
		if (title[cnt] == null) {
			for(int i=0; i<=9; i++) {
				title[cnt] = Integer.toString(i);
				fullTitle(title, D, cnt+1);	
				title[cnt] = null;
							
			}
		} else {
			fullTitle(title, D, cnt+1);	
		}

	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		int D = 1; // 세자리 수부터 시작
		while(hashList.size() < N) {
			pos = 0;
			isSelected = new boolean[D];
			permutation(0, 0, D);
			++D;
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>(hashList);
	
		list.sort(Comparator.naturalOrder());
		System.out.println(list.get(N-1));
		
		br.close();
	} // main

}
