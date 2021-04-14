import java.io.*;
import java.util.*;
// 210414

public class Main_BJ_17144_미세먼지안녕 {
	static int R, C, T;
	static int[][] map;
	static int r1, c1, r2, c2;
	
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	
	static int[] dr2 = {1, 0, -1, 0};
	static int[] dc2 = {0, 1, 0, -1};	
	
	static int[][] move() {
		int[][] newMap = new int[R+1][C+1];
		
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j]==-1) {
					newMap[i][j] = -1;
					continue;
					
				}
				newMap[i][j] += map[i][j];
				
				for(int d=0; d<4; d++) {
					int nr = i+dr1[d];
					int nc = j+dc1[d];
					
					if(nr<1 || nr>R || nc<1 || nc>C || newMap[nr][nc]==-1) continue;
					
					newMap[nr][nc] += map[i][j]/5;
					// 몇 개의 인접 칸으로 먼지를 보냈는지 세어서 한거번에 뺄 필요 없이
					// 보낼 때 마다 빼주면 된다.
					newMap[i][j] -= map[i][j]/5;			
				}
			}
		}
		
		return newMap;
	}
	
	static void purify() {
		// 위쪽 공기청정기
		int d=0;
		int nr, nc;
		int or=r1, oc=c1;
		
		while(d<4) {			
			nr = or + dr1[d];
			nc = oc + dc1[d];
			
			if(nr<1 || nr>r1 || nc<1 || nc>C) {
				++d;
				continue;
			}
			
			if(map[nr][nc]==-1) {
				map[or][oc]=0;
				break;
			}
			
			if(map[or][oc]==-1) {
				or=nr; oc=nc;
				continue;
			} // 사라짐
			else {
				map[or][oc] = map[nr][nc];
				or=nr; oc=nc;
			}
		}

		// 아래쪽 공기청정기
		d=0;
		nr=0; nc=0;
		or=r2; oc=c2;
		
		while(d<4) {			
			nr = or + dr2[d];
			nc = oc + dc2[d];
			
			if(nr<r2 || nr>R || nc<1 || nc>C) {
				++d;
				continue;
			}		
			
			if(map[nr][nc]==-1) {
				map[or][oc]=0;
				break;
			}
			
			if(map[or][oc]==-1) {
				or=nr; oc=nc;
				continue;
			} // 사라짐
			else {
				map[or][oc] = map[nr][nc];
				or=nr; oc=nc;
			}
		}
		
	}
	
	static int solve() {
		
		for(int i=1; i<=T; i++) {
			map = move();
			purify();
		}
		
		int res=0;
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j]==-1) continue;
				res += map[i][j];
			}
		}
		return res;
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_17144_미세먼지안녕.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		T = stoi(st.nextToken());
		
		map = new int[R+1][C+1];
		c1 = 1; c2 = 1;
		
		for(int i=1; i<=R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=C; j++) {
				map[i][j] = stoi(st.nextToken());
				if(map[i][j]==-1) {
					if(r1==0) r1 = i;
					else r2 = i;
				} 
			}
		}

		System.out.println(solve());;
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
