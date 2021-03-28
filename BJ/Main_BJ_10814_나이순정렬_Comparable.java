import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
//210328

public class Main_BJ_10814_나이순정렬_Comparable {
	
	static class Member implements Comparable<Member>{
		int age;
		String name;
		
		public Member(int age, String name) {
			this.age = age;
			this.name = name;
		}
		
		@Override
		public int compareTo(Member o) {
			return Integer.compare(this.age, o.age);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_BJ_10814_나이순정렬.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = stoi(br.readLine());
		
		Member[] members = new Member[N];
		
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			members[i] = new Member(stoi(st.nextToken()), st.nextToken());
		}
		
		// 정렬
		Arrays.sort(members);
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(members[i].age + " " + members[i].name + "\n");
		}
		System.out.println(sb.toString());
		
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
}
