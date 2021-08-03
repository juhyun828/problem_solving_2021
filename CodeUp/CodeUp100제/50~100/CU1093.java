import java.util.Scanner;

// 210124
// 1093 : [기초-1차원배열] 이상한 출석 번호 부르기1

public class CU1093 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		int[] stu = new int[24]; // 학생 23명
		for (int i = 0; i < num; i++) {
			stu[sc.nextInt()] += 1; 
		}
		
		for (int i = 1; i < stu.length; i++) {
			System.out.print(stu[i] + " ");
		}
		sc.close();
	}
}