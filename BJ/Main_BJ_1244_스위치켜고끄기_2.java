import java.io.*;
import java.util.*;
// 210226

public class Main_BJ_1244_스위치켜고끄기 {
	static int[] arr;
	static void flip(int idx) {
		if(arr[idx]==1) arr[idx]=0;
		else arr[idx]=1;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 스위치 개수
		arr = new int[N+1];
		for(int i=0; i<N; i++) {
			arr[i+1] = sc.nextInt();
		}
		int SN = sc.nextInt();
	
		for(int i=0; i<SN; i++) {
			int gender = sc.nextInt(); // 남 1 여 2
			int num = sc.nextInt(); 
			if(gender==1) {
				for(int j=1; j<=N; j++) {
					if(j%num==0) flip(j);
				}
			} else if(gender==2) {
				flip(num);
				for(int k=1; k<=N; k++) {
					int l=num-k, r=num+k;
					if(l>=1&&r<=N&&arr[l]==arr[r]) {
						flip(l); flip(r);
					} else {
						break;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<arr.length; i++) {
			sb.append(arr[i] + " ");
			if(i%20==0) sb.append("\n");
		}
		System.out.println(sb.toString());
		
		sc.close();
	} // main
}
