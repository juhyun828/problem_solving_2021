import java.util.Scanner;

// 210124
// 1095 : [기초-1차원배열] 이상한 출석 번호 부르기3

public class CU1095 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		
		int min = 2147000000;
		for (int i=0; i<num; i++) {
			int stuNum = sc.nextInt();
			if (min > stuNum) min = stuNum;
		}
		System.out.println(min);
		
		sc.close();
	}
}