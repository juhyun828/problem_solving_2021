import java.util.Scanner;

// 210126
// 1416 : 2진수 변환

public class CU1416 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int d = sc.nextInt();
		
		recursive(d);
		
	}
	
	public static void recursive(int num) {
		if (num<=1) System.out.print(num);
		else {
			recursive(num/2);
			System.out.print(num%2);			
		}
	}
}