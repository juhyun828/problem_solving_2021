import java.util.Scanner;

public class Main_BJ_2941_크로아티아알파벳_2 {
	static String[] two = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		int cnt = 0;
		
		for(int i=0; i<two.length;i++) {
			if (str.contains(two[i])) {
				++cnt;
				str = str.replaceFirst(two[i], " ");
			}
		}
		
		str = str.replaceAll(" ", "");
		System.out.println(cnt + str.length());

	}
}
