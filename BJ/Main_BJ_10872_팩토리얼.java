import java.io.*;
import java.util.*;
// 210211

public class Main_BJ_10872_팩토리얼 {
	
	static int factorial(int num) {
		if (num <= 1) return 1;
		return num * factorial(num-1);
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println(factorial(sc.nextInt()));
		
		sc.close();
	}

}
