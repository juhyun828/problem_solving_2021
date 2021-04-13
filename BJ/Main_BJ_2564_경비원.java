import java.io.*;
import java.util.*;
// 210413

public class Main_BJ_2564_경비원 {
	static int w, h, n;
	static int[] x; // 경비원
	static int[][] target; // 상점: 0 방향, 1 거리
	// 1 북 2 남 3 서 4 동
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_2564_경비원.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		w = stoi(st.nextToken());
		h = stoi(st.nextToken());
		
		n = stoi(br.readLine()); // 상점 개수
		target = new int[n][2];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			target[i][0] = stoi(st.nextToken());
			target[i][1] = stoi(st.nextToken());
		}
		
		x = new int[2];
		st = new StringTokenizer(br.readLine(), " ");
		x[0] = stoi(st.nextToken()); 
		x[1] = stoi(st.nextToken()); 
		
		int res = 0;
		
		for(int i=0; i<n; i++) {
			
			if(x[0] == 1) { // 북
				if(target[i][0] == 1) {
					res += Math.abs(x[1]-target[i][1]);
					
				} else if(target[i][0] == 2) {
					res += h + Math.min( x[1]+target[i][1] , w-x[1] + w-target[i][1]);
					
				} else if(target[i][0] == 3) {
					res += x[1]+target[i][1];
					
				} else if(target[i][0] == 4) {
					res += w-x[1] + target[i][1];
				}				
				
			} else if (x[0] == 2) { // 남
				if(target[i][0] == 1) {
					res += h + Math.min( x[1]+target[i][1] , w-x[1] + w-target[i][1]);
					
				} else if(target[i][0] == 2) {
					res += Math.abs(x[1]-target[i][1]);
					
				} else if(target[i][0] == 3) {
					res += x[1] + h-target[i][1];
					
				} else if(target[i][0] == 4) {
					res += w-x[1] + h-target[i][1];
				}
					
			} else if (x[0] == 3) { // 서
				if(target[i][0] == 1) {
					res += x[1] + target[i][1];
					
				} else if(target[i][0] == 2) {
					res += h-x[1] + target[i][1];
					
				} else if(target[i][0] == 3) {
					res += Math.abs(x[1]-target[i][1]);
					
				} else if(target[i][0] == 4) {
					res+= w + Math.min(x[1]+target[i][1], w-x[1]+w-target[i][1]);
				}				
				
			} else if (x[0] == 4) { // 동
				if(target[i][0] == 1) {
					res += x[1] + w-target[i][1];
					
				} else if(target[i][0] == 2) {
					res += h-x[1] + w-target[i][1]; 
					
				} else if(target[i][0] == 3) {
					res +=w + Math.min(x[1]+target[i][1], w-x[1]+w-target[i][1]);
					
				} else if(target[i][0] == 4) {
					res += Math.abs(x[1]-target[i][1]);
					
				}			
				
			}
			
		} // for
		System.out.println(res);

		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
