import java.io.*;
import java.util.*;
// 210317

public class Solution_D_1767_프로세서연결하기_2 {
	static int N, maxCnt, minLen, CN;
	static int[][] map;
	static ArrayList<Pos> coreList;
	static int[] dr = {-1, 0, 0, 1}; // 0  상 1 좌 2 우 3 하
	static int[] dc = {0, -1, 1, 0};
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void subset(int cIdx, int cCnt, int cLen, int L) {
		if(L==CN) {
			// maxCnt, minLen 갱신
			if(maxCnt<cCnt) {
				maxCnt = cCnt;
				minLen = cLen;
			} else if(maxCnt==cCnt) {
				minLen = Math.min(minLen, cLen);
			}
			return;
		}
		
		// cIdx번째 코어 선택
		for(int d=0; d<4; d++) {
			int tmpLen = go(coreList.get(cIdx).r, coreList.get(cIdx).c, d, 0);
			if(tmpLen>0) { // 갈 수 있음
				setStatus(coreList.get(cIdx).r, coreList.get(cIdx).c, d, 2); // 2 전선
				subset(cIdx+1, cCnt+1, cLen+tmpLen, L+1);
				setStatus(coreList.get(cIdx).r, coreList.get(cIdx).c, d, 0); // 0 빈칸 복귀
			}
		}
		// cIdx번째 코어 선택x
		subset(cIdx+1, cCnt, cLen, L+1);
	}
	
	static void setStatus(int r, int c, int d, int status) {
		if(r<0 || r>N || c<0 || c>N) return;
		int nr = r + dr[d]; int nc = c + dc[d];
		if(nr>=0 && nr<N && nc>=0 && nc<N) {
			map[nr][nc] = status;
			setStatus(nr, nc, d, status);
		}
	}
	
	static int go(int r, int c, int d, int len) {
		if(r==0 || r==(N-1) || c==0 || c==(N-1)) return len;
		
		int nr = r + dr[d]; int nc = c + dc[d];
		if(map[nr][nc]==0) {
			return go(nr, nc, d, len+1);
		}
		else return 0;
	}
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			N = stoi(br.readLine().trim());
			maxCnt = 0; minLen = Integer.MAX_VALUE;
			map = new int[N][N];
			CN = 0;
			coreList = new ArrayList<>();
				
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = stoi(st.nextToken());
					if(map[i][j]==1) { //  코어
						if(i==0 || i==(N-1) || j==0 || j==(N-1)) continue;
						coreList.add(new Pos(i, j));
						++CN;		
					}
				}
			} // input
			subset(0, 0, 0, 0);
			sb.append("#" + tc + " " + minLen+"\n");

		} // tc
		
		System.out.println(sb.toString());
		br.close();
	}