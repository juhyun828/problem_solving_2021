import java.io.*;
import java.util.*;

// 210214
public class Main_BJ_3040_백설공주와일곱난쟁이 {
	static int[] input;
	static int[] seven;
	
	static void combination(int cnt, int start, int total) {
		if (total > 100) return;
		
		if (cnt==7) {
			if (total==100) {
				StringBuilder sb = new StringBuilder();
				for(int i=0; i<7; i++) {
					sb.append(seven[i] + "\n");
				}
				System.out.println(sb.toString());
			}
			return;
		}
		
		if(cnt>=9) return;
		
		for(int i=start; i<9; i++) {
			seven[cnt] = input[i];
			combination(cnt+1, i+1, total+input[i]);
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_3040_백설공주와일곱난쟁이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = new int[9];
		seven = new int[7];
		
		for(int i=0; i<9; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		combination(0, 0, 0);
		// System.out.println(Arrays.toString(input));
		br.close();
	}
}
