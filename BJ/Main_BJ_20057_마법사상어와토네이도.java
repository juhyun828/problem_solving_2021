import java.io.*;
import java.util.*;
// 210416

public class Main_BJ_20057_마법사상어와토네이도 {
	static int N;
	static int[][] map;
	static int out;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_20057_마법사상어와토네이도.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		map = new int[N+1][N+1];
		out = 0;
	
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=N; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		rotate();
		System.out.println(out);

		br.close();
	}
	
	// 좌 하 우 상
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {-1, 0, 1, 0};
	
	static void rotate() {
		int far=0;
		int num = 0;
		int or = N/2+1, oc = N/2+1;
		int nr = or, nc = or;
		
		for(int k=1; k<=N/2; k++) {
			for(int d=0; d<4; d++) {
				if(d==0 || d==2) ++far;
				for(int s=1; s<=far; s++) {
					nr = or + dr[d]*s;
					nc = oc + dc[d]*s;
					move(nr, nc, d);
					//map[nr][nc] = ++num;
				}
				or = nr; oc = nc;
			}
		} // k
		int d=0;
		for(int s=1; s<=far; s++) {
			nr = or+dr[d]*s;
			nc = oc+dc[d]*s;
			move(nr, nc, d);
			//map[nr][nc] = ++num;
		}
	}
	
	// 좌 하 우 상
	static int[] percentage = {1, 1, 2, 2, 5, 7, 7, 10, 10};
	
	static int[][] ddr = { 
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, // 좌
			{ -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 }, // 하
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, // 우
			{ 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 } // 상
	};
	static int[][] ddc = { 
			{ 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, // 좌
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, // 하
			{ -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 }, // 우
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } // 상
	};
	
	static void move(int nr, int nc, int di) {
		int nnr, nnc, amount;
		int now = map[nr][nc];
		
		for(int d=0; d<=8; d++) {
			nnr = nr + ddr[di][d];
			nnc = nc + ddc[di][d];
			amount = (int)now * percentage[d]/100;
			
			if(nnr<1 || nnr>N || nnc<1 || nnc>N) {
				out += amount;
				map[nr][nc] -= amount;
			
			}
			else {
				map[nnr][nnc] += amount;
				map[nr][nc] -= amount;
			}
		}	
		
		// 남은 양은 a로
		nnr = nr + ddr[di][9];
		nnc = nc + ddc[di][9];
		
		if(nnr<1 || nnr>N || nnc<1 || nnc>N) {
			out += map[nr][nc];
			map[nr][nc] = 0; 
		} else {
			map[nnr][nnc] += map[nr][nc];
			map[nr][nc] = 0; 
		}
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
