import java.util.Scanner;

// 210124
// 1096 : [기초-2차원배열] 바둑판에 흰 돌 놓기

public class CU1096 {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		int[][] intArr = new int[20][20];
		
		for (int i=0; i<num; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();	
			intArr[x][y] = 1;
		}
		
		for (int i=1; i<intArr.length; i++) {
			for (int j=1; j<intArr[i].length; j++) {
				System.out.print(intArr[i][j] + " ");
			}
			System.out.println();
		}
		
		sc.close();
	}
}