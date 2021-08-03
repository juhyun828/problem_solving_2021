import java.io.*;
import java.util.*;

// 210214

public class Main_BJ_2961_도영이가만든맛있는음식 {

	static int N; // 재료 가지 수
	static int[][] arr; // 재료의 신맛, 쓴맛 순서대로
	static int min = Integer.MAX_VALUE;
	static boolean[] isSelected; // 재료를 선택했는지 여부
	
	static void subset(int cnt, int sour, int bitter) {
		if (cnt == N) {
			// 신맛과 쓴맛의 차이가 작아야 함
			
			// System.out.println(Arrays.toString(isSelected) + " " + sour + " "+ bitter);
			
			// 물은 재료x 적어도 하나는 선택해야 함
			boolean flag = false;
			for(int i=0; i<N; i++) {
				if (isSelected[i]) {
					flag = true;
					break;
				}
			}
			if (!flag) return;
			
			min = Math.min(min, Math.abs(sour-bitter));
			
			return;
		}
		
		isSelected[cnt] = true; // cnt번째 재료를 선택했을 경우
		subset(cnt+1, sour*arr[cnt][0], bitter+arr[cnt][1]);
		
		isSelected[cnt] = false; // cnt번째 재료를 선택하지 않았을 경우
		subset(cnt+1, sour, bitter);
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_2961_도영이가만든맛있는음식.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2]; // 신맛, 쓴맛 순서대로
		isSelected = new boolean[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		subset(0, 1, 0); // 쓴맛의 합은 곱으로 구하기 때문에 1로 초기값을 주어야 함
		System.out.println(min);
		br.close();
	}

}
