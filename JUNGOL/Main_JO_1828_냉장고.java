import java.io.*;
import java.util.*;

// 210216
public class Main_JO_1828_냉장고 {

	static int stoi(String st) {
		return Integer.parseInt(st);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_JO_1828_냉장고.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = stoi(br.readLine());
		int[][] arr = new int[N][2];
		StringTokenizer st = null;

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = stoi(st.nextToken());
			arr[i][1] = stoi(st.nextToken());
		}

		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int diff = -(Integer.compare(o1[1], o2[1]));
				return diff!=0 ? diff : Integer.compare(o1[0], o2[0]);
			}
		});
		;
		int cnt = 1;
		int minMax = arr[0][0];
		
		for(int i=1; i<N; i++) {
			if (arr[i][1] >= minMax) {
				minMax = Math.max(minMax, arr[i][0]);
			} else {
				++cnt;
				minMax = arr[i][0];
			}
		}
		
		System.out.println(cnt);
	
		br.close();
	}
}
