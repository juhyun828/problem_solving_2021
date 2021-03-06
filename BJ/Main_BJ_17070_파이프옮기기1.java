import java.io.*;
import java.util.*;
// 0306

public class Main_BJ_17070_파이프옮기기1 {
	static int N, cnt=0;
	static int[][] map;
	static int[] dr = {0, 1, 1}; // 가로, 세로, 대각선 순
	static int[] dc = {1, 0, 1};
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void move(int[] ip1, int[] ip2) {
		
		if(ip2[0]==N && ip2[1]==N) {
			++cnt;
			return;
		}
		
		for(int d=0; d<3; d++) {
			int nr = ip2[0] + dr[d];
			int nc = ip2[1] + dc[d];
			
			// 범위 체크
			if (nr<=0 || nr>N || nc<=0 || nc>N || map[nr][nc]==1) continue;
			
			if (ip1[0]==ip2[0] && d==1) continue; // 가로로 놓인 경우 세로 방향 이동 불가
			if (ip1[1]==ip2[1] && d==0) continue; // 세로로 놓인 경우 가로 방향 이동 불가
	
			// 대각선 방향으로 갈 경우 우, 좌 빈칸 여부도 확인
			if (d==2 && (map[nr-1][nc]==1 || map[nr][nc-1]==1)) continue;
			
			// 이동 가능
			move(ip2, new int[] {nr, nc});
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_17070_파이프옮기기1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = stoi(br.readLine());
		map = new int[N+1][N+1]; // 1행 1열부터 시작
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=N; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
	
		move(new int[] {1,1},new int[] {1,2});
		
		System.out.println(cnt);
		
		br.close();
	} // main
}
