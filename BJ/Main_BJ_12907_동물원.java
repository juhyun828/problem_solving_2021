import java.util.*;
// 0401

public class Main_BJ_12907_동물원 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] cnt = new int[41];
		
		//cnt[i] = 0;
		for(int i=0; i<n; i++) {
			int x = sc.nextInt();
			cnt[x] += 1;
		}
		
		if(cnt[0] > 0) {
			System.out.println(0);
			System.exit(0);
		}
		
		for(int i=1; i<=40; i++) {
			if(cnt[i] > cnt[i-1]) {
				System.out.println(0);
				System.exit(9);
			}
		}
		
		long ans = 1;
		int i=0;
		while(cnt[i]==2) {
			ans *= 2;
			i += 1;
		}
		if(cnt[i]==1) {
			ans *= 2;
		}
		System.out.println(ans);
	}

}