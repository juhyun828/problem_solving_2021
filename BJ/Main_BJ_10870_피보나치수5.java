import java.io.*;
import java.util.*;
// 210211

public class Main_BJ_10870_피보나치수5 {

	static int fibo(int num) {
		if (num <= 1) return num;
		return fibo(num-2) + fibo(num-1);
	}
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println(fibo(sc.nextInt()));
		sc.close();
	}
}
