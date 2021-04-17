import java.io.*;
import java.util.*;
// 210416

public class Main_BJ_19236_청소년상어_fail {
	static int[][][] map; // 0~4 // null은 상어가 있거나 빈칸 // 빈칸일 경우 상어의 현재 위치랑 비교하기
	static int max; // 먹을 수 있는 물고기 번호의 최댓값
	
	static int[] dr = { -1,-1, 0,  1, 1, 1, 0, -1   }; // ↑,   ↖,  ←,  ↙,   ↓,   ↘,  →,  ↗
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_19236_청소년상어.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
	
		map = new int[4][4][2];
		max = 0;
		
		int idx, d;
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<4; j++) {
				idx = stoi(st.nextToken());
				d = stoi(st.nextToken())-1;
				map[i][j][0] = idx;
				map[i][j][1] = d;
			}
		}
		
		solve();
			
		br.close();
		
	}
	
	static void solve() {
		// 상어는 처음엔 0, 0번 자리의 물고기를 먹는다.
		
		sharkMove(0, 0, map[0][0][1], map[0][0][0], map); // 상어 위치 r, c, 상어 방향, 먹은 물고기 번호 합
		
		System.out.println(max);
		
	}

	static void printMap(int[][][] imap) {
		System.out.println("==============");
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(imap[i][j][0]==-1)	System.out.print("[          ] ");
				else System.out.print(imap[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 상어가 이동하며 물고기를 먹는다.
	static void sharkMove(int sharkR, int sharkC, int sharkDir, int total, int[][][] imap) {
		// dfs 돌 때 마다 배열 복사하여 이용
		int[][][] mapCopy = new int[4][4][2];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				for(int k=0; k<2; k++) 
					mapCopy[i][j][k] = imap[i][j][k]; 
			}
		}
		
		total += mapCopy[sharkR][sharkC][0];
		mapCopy[sharkR][sharkC][0] = -1; // 물고기 죽음
		mapCopy[sharkR][sharkC][1] = -1;
		fishMove(sharkR, sharkC, mapCopy); // 물고기 이동
		
		int nr, nc, nd;
		boolean moveable = false;
		// 상어는 최대 세 칸 멀리 간다.
		
		// 갈 수 있는 칸들 찾기
		for(int k=1; k<=3; k++) {
			// 물고기가 있는 칸으로만 갈 수 있다.
			nr = sharkR + dr[sharkDir]*k;
			nc = sharkC + dc[sharkDir]*k;
			
			if(nr<0 || nr>= 4 || nc<0 || nc>=4 || mapCopy[nr][nc][0]==-1) continue;
			// 이동 가능
			moveable = true;
			nd = mapCopy[nr][nc][1];
			
			sharkMove(nr, nc, nd, total, mapCopy);
		}
		
		if(!moveable) {
			// 더이상 이동할 수 없음
//			System.out.println(total);
			max = Math.max(max, total);
		} 
	}
	
	static int[] findFishPos(int idx, int[][][] imap) {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(imap[i][j][0]==idx) return new int[] {i, j};
			}
		}
		return null;
	}
	
	// 물고기가 이동한다.
	static void fishMove(int sharkR, int sharkC, int[][][] imap) {
		int nr=-1, nc=-1, nd=-1;
		int fishR, fishC, fishDir;
		boolean moveable;
		
		for(int i=1; i<=16; i++) {
			// 먹힌 물고기는 제외

			int[] fishPos = findFishPos(i, imap);
			if(fishPos==null) continue;
			
			fishR = fishPos[0]; 
			fishC = fishPos[1];
			fishDir = imap[fishR][fishC][1];
			
			moveable = false;
			for(int d=0; d<8; d++) {
				nr = fishR + dr[(fishDir + d)% 8];
				nc = fishC + dc[(fishDir + d)% 8];
				nd = (fishDir + d)% 8;
				
				if(nr<0 || nr>= 4 || nc<0 || nc>=4 || (nr==sharkR && nc==sharkC))
					continue;
				else {
					moveable = true;
					break;
				}
			}
			
			// 이동할 수 있는 칸이 없음
			if(!moveable) continue;
			
			// 이동할 수 있으면 
			int otherIdx = imap[nr][nc][0];
			int otherDir = imap[nr][nc][1];
			
			// 새 물고기 칸
			imap[nr][nc][0] = i;
			imap[nr][nc][1] = nd;
			
			// 교체할 물고기 칸
			imap[fishR][fishC][0] = otherIdx;
			imap[fishR][fishC][1] = otherDir;
			
		}
		
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}

