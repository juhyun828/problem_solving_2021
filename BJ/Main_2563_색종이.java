import java.io.*;
import java.util.*;

// 210209 

public class Main_2563_색종이 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[101][101];
		int area = 0;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()); // X 좌표
			int y = Integer.parseInt(st.nextToken()); // Y 좌표
			
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k <10; k++) {
					if (arr[x+j][y+k] == 1) continue;
					arr[x+j][y+k] = 1;
					++area;
				}
			}
		}

		System.out.println(area);
		br.close();
	} // main

}
