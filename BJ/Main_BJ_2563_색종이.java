import java.io.*;
import java.util.*;

// 210222

public class Main_BJ_2563_색종이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int cnt = 0;
		boolean[][] visited = new boolean[101][101];
		
		for(int i=0; i<N; i++) {
			int x = sc.nextInt(); int y = sc.nextInt();
			for(int xi=0; xi<10; xi++) {
				for(int yi=0; yi<10; yi++) {
					if (visited[x+xi][y+yi]) continue;
					++cnt;
					visited[x+xi][y+yi] = true;
				}
			}
		}
		
		System.out.println(cnt);
		sc.close();
	}

}
