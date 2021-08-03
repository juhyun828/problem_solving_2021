import java.io.*;
import java.util.*;
// 210418

public class Main_BJ_19236_청소년상어 {
	static int max;
	
	static int[] dr = { -1,-1, 0,  1, 1, 1, 0, -1   }; // ↑,   ↖,  ←,  ↙,   ↓,   ↘,  →,  ↗
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_19236_청소년상어.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int[][][] map = new int[4][4][2]; // 물고기 번호와 방향 테이블
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
		
		dfs(map, 0, 0, 0);
		System.out.println(max);
			
		br.close();
		
	}
	
	// 모든 물고기 회전 및 이동
	static void moveFish(int[][][] imap, int sharkR, int sharkC) {
		// 1번부터 16번 물고기까지
		for(int idx=1; idx<=16; idx++) {
			// 해당 물고기 위치
			int[] fishPos = findFishPos(imap, idx);
			if(fishPos==null) continue; // 죽은 물고기
			
			int fishR = fishPos[0];
			int fishC = fishPos[1];
			int fishDir = imap[fishR][fishC][1];
		
			for (int d=0; d<8; d++) {
				int nr = fishR + dr[fishDir];
				int nc = fishC + dc[fishDir];
				
				if(nr<0 || nr>=4 || nc<0 || nc>=4 || (nr==sharkR && nc==sharkC)) {
					fishDir = (fishDir+1)%8;
					continue;
				}
				// 이동 가능하면 이동시킴
				
				int otherIdx = imap[nr][nc][0];
				int otherDir = imap[nr][nc][1];
				
				// 새 위치에 idx 번째 물고기
				imap[nr][nc][0] = idx;
				imap[nr][nc][1] = fishDir;
				
				// idx 위치에 새 물고기
				imap[fishR][fishC][0] = otherIdx;
				imap[fishR][fishC][1] = otherDir;
				break;
			}
		}
	}
	
	// 상어가 갈 수 있는 위치에 따른 경우들을 탐색,dfs
	static void dfs(int[][][] imap, int sharkR, int sharkC, int total) {
		
		int[][][] mapCopy = new int[4][4][2];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				mapCopy[i][j][0] = imap[i][j][0];
				mapCopy[i][j][1] = imap[i][j][1];
			}
		}
		
		// 해당 위치 물고기 먹음
		total += mapCopy[sharkR][sharkC][0];
		mapCopy[sharkR][sharkC][0] = -1;
		
		moveFish(mapCopy, sharkR, sharkC);
		
		// 상어가 갈 수 있는 위치들 찾기
		ArrayList<int[]> list = getAllSharkPos(mapCopy, sharkR, sharkC);
		if(list==null || list.size()==0) {
			max = Math.max(max, total);
			return;
		} else {
			for (int i=0; i<list.size(); i++) {
				int nr = list.get(i)[0];
				int nc = list.get(i)[1];
				
				dfs(mapCopy, nr, nc, total);
			}
		}
	}
	
	// 상어가 현재 위치에서 먹을 수 있는 모든 물고기 위치 반환
	static ArrayList<int[]> getAllSharkPos(int[][][] imap, int sharkR, int sharkC) {
		
		ArrayList<int[]> list = new ArrayList<int[]>();
		int sharkDir = imap[sharkR][sharkC][1];
		
		int nr, nc;
		for(int k=1; k<=3; k++) {
			nr = sharkR + dr[sharkDir]*k;
			nc = sharkC + dc[sharkDir]*k;
			
			if(nr<0 || nr>=4 || nc<0 || nc>=4 || imap[nr][nc][0]==-1) continue;
			list.add(new int[] {nr, nc});
		}
		return list;
	}
	
	static int[] findFishPos(int[][][] imap, int idx) {
		for (int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(imap[i][j][0] == idx) {
					return new int[] {i, j};
				} 
			}
		}
		return null;
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
