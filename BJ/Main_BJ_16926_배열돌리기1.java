import java.io.*;
import java.util.*;
//210420

public class Main_BJ_16926_배열돌리기1 {
	static int N, M, R;
	static int[][] map;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_16926_배열돌리기1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		R = stoi(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		solve();
		
		// print
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void solve() {
		// R번 회전
		for(int t=1; t<=R; t++) {
			rotate();
		}
	}
	
	static void rotate() {
		// sub네모 개수: min(가로, 세로)/2
		
		for(int sub=0; sub<Math.min(N, M)/2; sub++) {
			int or = sub, oc = sub;
			int d = 0;
			int startValue = map[or][oc];
			// 처음 map[or][oc] 값을 저장해두었다가
			// 마지막 map[nr][nc]에 저장해야 한다.
			while(d<4) {
				int nr = or + dr[d];
				int nc = oc + dc[d];
				if(nr<sub || nr>=N-sub || nc<sub || nc>=M-sub) {
					++d;
					continue;
				}
				map[or][oc] = map[nr][nc];
				or = nr; oc = nc;
			}
			// 마지막 map[nr][nc] = map[시작r+1][시작c]
			map[sub+1][sub] = startValue;
		}
	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}

