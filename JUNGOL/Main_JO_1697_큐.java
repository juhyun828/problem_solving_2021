import java.util.Scanner;

// 210126
// 1697 : 큐(queue)

public class Main_JO_1697_큐 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] queue = new int[200];
		int front = 1;
		int rear = 0;
		
		for (int i=0; i<N; i++) {
			String str = sc.next();
			if (str.equals("i")) { // push
				queue[++rear] = sc.nextInt();
			} else if (str.equals("o")) { // pop
				if (front>rear) {
					System.out.println("empty");
				} else {
					System.out.println(queue[front]);
					queue[front] = 0;
					front++;
				}
				
			} else {
				System.out.println(rear-front+1);
			}
		}
		
		sc.close();
	} // main

}