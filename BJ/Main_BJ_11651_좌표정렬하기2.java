import java.io.*;
import java.util.*;
// 210328

public class Main_BJ_11651_좌표정렬하기2 {
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_BJ_11651_좌표정렬하기2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = stoi(br.readLine());
		int[][] arr =new int[N][2];
		
		int x, y;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = stoi(st.nextToken());
			y = stoi(st.nextToken());
			arr[i][0] = x;
			arr[i][1] = y;
		}
	
		// y 증가, x 증가순 정려
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return Integer.compare(o1[0], o2[0]);
				}
				return Integer.compare(o1[1], o2[1]);
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
