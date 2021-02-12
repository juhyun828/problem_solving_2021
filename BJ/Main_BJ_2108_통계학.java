import java.io.*;
import java.util.*;
// 210212

public class Main_BJ_2108_통계학 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		HashMap<Integer, Integer> cntHash = new HashMap<>();
		
		int sum=0;
		int freq = 0;
		
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
			sum += arr[i];
			if (cntHash.get(arr[i]) == null) {
				cntHash.put(arr[i], 1);
			} else {
				cntHash.put(arr[i], cntHash.get(arr[i]) + 1);
			}
			
			freq = Math.max(freq, cntHash.get(arr[i]));
		}

		System.out.println(cntHash.values());
		System.out.println(freq);
		
		for()
		
		float avg = (float) sum / N; 
		Arrays.sort(arr);
		

		sc.close();
	}

}
