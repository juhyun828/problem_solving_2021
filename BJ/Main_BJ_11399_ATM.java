import java.io.*;
import java.util.*;

// 210216
public class Main_BJ_11399_ATM {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		int t1 = 0;
		int t2 = 0;
		
		for(int i : arr) {
			t1 += i;
			t2 += t1;
		}
		System.out.println(t2);
		
		sc.close();
 	}

}
