import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

// 210327

// 1. Comparable
// - Comparable은 java.lang (기본) 패키지 안에 있어 import 없이 사용 가능하다.
// - Comparable은 정렬 기준이 없는 객체(Object)에 정렬 기준을 만들어 주는 것이다.
// - Comparable은 기본 자료형에 쓰지 않는다.

public class Main_BJ_11650_좌표정렬하기_Comparable {

	static class Pos implements Comparable<Pos>{
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Pos o) {
			if(Integer.compare(this.x, o.x) == 0) {
				return Integer.compare(this.y, o.y);
			}
			return Integer.compare(this.x, o.x);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_BJ_11650_좌표정렬하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = stoi(br.readLine());
		Pos[] arr = new Pos[N];
		
		int x, y;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = stoi(st.nextToken());
			y = stoi(st.nextToken());
			arr[i] = new Pos(x, y);
		}
		
		Arrays.sort(arr);
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(arr[i].x + " " + arr[i].y + "\n");
		}
		
		System.out.println(sb.toString());
		
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
