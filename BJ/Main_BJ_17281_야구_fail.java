import java.io.*;
import java.util.*;
// 210308

public class Main_BJ_17281_야구_fail {
	
	static int N; // 이닝 수
	static int[][] inputArr; // 각 선수가 이닝에서 얻는 결과
	static int[] ord; // 타순
	static boolean[] visited;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void permutation(int cnt) {
		if(cnt>9) {
			System.out.println(Arrays.toString(ord));
			return;
		}
		
		for(int i=1; i<=9; i++) {
			if(cnt==4) {
				 permutation(cnt+1);
				 continue;
			}
			if(visited[i]) continue;
		
			visited[i] = true;
			ord[cnt] = i; // cnt번째 타순에 i번째 선수
			permutation(cnt+1);
			visited[i] = false;
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input__BJ_17281_야구.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		inputArr = new int[N+1][10]; // 0-dummy, 이닝과 선수는 1번부터
		
		StringTokenizer st;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=9; j++) {				
				inputArr[i][j] = stoi(st.nextToken());
			}
		}
		visited = new boolean[10];
		ord = new int[10]; // 0-dummy
		ord[4] = 1; // 네번째 타순은 1번으로 고정됨
		visited[1] = true;
		
		permutation(1);
		br.close();
	}
}
