
import java.io.*;
import java.util.*;
// 210223

public class Main_BJ_2477_참외밭 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int max = 0; int min = 0;
		int[] arr= new int[6];
		
		for(int i=0; i<6; i++) {
			int d = sc.nextInt();
			arr[i] = sc.nextInt();
		}
		
		for(int i=0; i<6; i++) {
			int cur = arr[i] * arr[(i+1)%6];
			if (cur>max) {
				max = cur;
				min = arr[(i+4)%6] * arr[(i+3)%6];
			}
		}
		
		System.out.println((max-min)*N);

		sc.close();
	}

}
