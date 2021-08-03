import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// 1019 : [기초-입출력] 연월일 입력받아 그대로 출력하기
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		String[] dates = str.split("\\."); // Tokenizer?
		 
		String year = String.format("%04d", Integer.parseInt(dates[0]));
		// %02d를 사용하면 2칸을 사용해 출력하는데,
		// 한 자리 수인 경우 앞에 0을 붙여 출력한다.
		String month = String.format("%02d", Integer.parseInt(dates[1]));
		String day = String.format("%02d", Integer.parseInt(dates[2]));
		
		System.out.println(year + "." + month + "." + day);
		
	}
}

