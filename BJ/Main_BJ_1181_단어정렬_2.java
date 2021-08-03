import java.io.*;
import java.util.*;
// 210328

public class Main_BJ_1181_단어정렬_2 {
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_BJ_1181_단어정렬.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = stoi(br.readLine());
		String[] arr = new String[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = br.readLine();
		}
	
		// 정렬
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() == o2.length()) {
					return o1.compareTo(o2);
				}
				return Integer.compare(o1.length(), o2.length());
			}
		});
		
		// 중복 제거하며 출력
		StringBuilder sb = new StringBuilder();
		sb.append(arr[0] + "\n");
		for(int i=1; i<N; i++) {
			if(!arr[i-1].equals(arr[i])) {
				sb.append(arr[i] + "\n");
			}
		}
		System.out.println(sb.toString());
	
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
