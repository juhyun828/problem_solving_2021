import java.io.*;
import java.util.*;
// 210212

public class Main_BJ_1181_단어정렬 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		HashSet<String> hash = new HashSet<String>();
		
		for(int i=0; i<N; i++) {
			hash.add(sc.next());
		}
		
		ArrayList<String> list = new ArrayList<String>(hash);
		
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() == o2.length()) {
					return o1.compareTo(o2);
				}
				return o1.length() - o2.length();
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<list.size(); i++) {
			sb.append(list.get(i) + "\n");
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}

}
