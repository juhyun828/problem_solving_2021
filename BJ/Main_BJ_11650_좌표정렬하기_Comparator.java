import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;;

// 210327

// 2. Comparator
// - Comparator는 java.util 패키지 안에 있는 '외부 정렬 기준'이다.
// - Comparator는 기본 정렬 기준과 다른 정렬 기준을 만들 때 사용한다.

public class Main_BJ_11650_좌표정렬하기_Comparator {
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_BJ_11650_좌표정렬하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = stoi(br.readLine());
		int[][] arr = new int[N][2];
		
		int x, y;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = stoi(st.nextToken());
			y = stoi(st.nextToken());
			arr[i][0] = x;
			arr[i][1] = y;
		}
		
		Arrays.sort(arr, new Comparator<int[]>() {
		@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return Integer.compare(o1[1], o2[1]);
				}
				return Integer.compare(o1[0], o2[0]);
			}
		});
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(arr[i][0] + " " + arr[i][1] + "\n");
		}
	
		System.out.println(sb.toString());
		
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
