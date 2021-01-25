import java.util.Scanner;

// 210125
// 1405 : 숫자 로테이션

public class CU1405 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		int[] intArr = new int[num];
		
		for (int i=0; i<num; i++) {
			intArr[i] = sc.nextInt();
		}
		
		for (int i=0; i<num; i++) {
			for (int j=i; j<num; j++) {
				System.out.print(intArr[j] + " ");
			}
			for (int z=0; z<i; z++) {
				System.out.print(intArr[z] + " ");
			}
			System.out.println();
		}		
		
		sc.close();
	}
}