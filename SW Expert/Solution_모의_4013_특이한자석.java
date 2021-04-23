import java.io.*;
import java.util.*;
// 210423

public class Solution_모의_4013_특이한자석 {
	static int K;
	static int[][] arr; // 톱니바퀴 번호, 8개의 N/S 정보
	static int[][] command;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_모의_4013_특이한자석.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			K = stoi(br.readLine());
			arr = new int[5][8]; // 톱니바퀴는 1번부터
			command = new int[K][2];
			
			for(int i=1; i<=4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<8; j++) {
					arr[i][j] = stoi(st.nextToken());
				}
			}
			
			// 회전 명령
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				command[i][0] = stoi(st.nextToken());
				command[i][1] = stoi(st.nextToken());
			}
			
			solve();
			sb.append("#" + tc + " " + count() + "\n");
		}
		
		System.out.println(sb.toString());
		
		br.close();
	}
	
	static void solve() {
		for(int k=0; k<K; k++) {
			v = new boolean[5];
			rotate(command[k][0], command[k][1]);
		}
	}

	static int count() {
		int score = 0;
		
		if(arr[1][0]==1) score += 1;
		if(arr[2][0]==1) score += 2;
		if(arr[3][0]==1) score += 4;
		if(arr[4][0]==1) score += 8;
		
		return score;
	}
	
	static void rotate(int idx, int dir) {
		if(v[idx]) return;
		v[idx]=true;
		
		boolean left=false, right=false;
		
		if(idx-1>=1 && arr[idx-1][2] != arr[idx][6]) left = true;
		if(idx+1<=4 && arr[idx][2] != arr[idx+1][6]) right =true;
		
		int[] res = new int[8];
		if(dir==1) {
			// 시계방향 회전
			for(int i=0; i<8; i++) {
				int ni = (i+dir)%8;
				res[ni] = arr[idx][i];
			}
			for(int i=0; i<8; i++) {
				arr[idx][i] = res[i];
			}
			
		} else if (dir==-1) {
			// 반시계방향 회전
			res[7] = arr[idx][0];
			for(int i=1; i<8; i++) {
				int ni = (i+dir)%8;
				res[ni] = arr[idx][i];
			}
			
			for(int i=0; i<8; i++) {
				arr[idx][i] = res[i];
			}
		}
		
		if(left) rotate(idx-1, dir*-1);
		if(right) rotate(idx+1, dir*-1);		
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}