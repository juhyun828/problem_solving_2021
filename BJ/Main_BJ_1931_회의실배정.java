import java.io.*;
import java.util.*;

// 210216
public class Main_BJ_1931_회의실배정 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 끝나는 시간이 짧고 시작하는 시간이 빠른

		int N = sc.nextInt();
		int[][] arr = new int[N][2];
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();	
			System.out.println(Arrays.toString(arr[i]));
		}

		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int diff = Integer.compare(o1[1], o2[1]);
				return diff!=0 ? diff : Integer.compare(o1[0], o2[0]);
			}
		});
		
		System.out.println("==============");
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		
		sc.close();
	}
}
