import java.io.*;
import java.util.*;
// 210423

public class Main_BJ_17143_낚시왕 {
	static int R, C, M, total;
	static int[] dr = {-1, 1, 0, 0}; // 상 하 우 좌
	static int[] dc = {0, 0, 1, -1};
	static Shark[][] map;
	
	static class Shark {
		int idx, s, d, z; // 번호, 속력, 이동방향, 크기

		public Shark(int idx, int s, int d, int z) {
			this.idx = idx;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_17143_낚시왕.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		M = stoi(st.nextToken());
		total = 0;
		map = new Shark[R+1][C+1];
		
		for(int i=1; i<=M; i++) {
			// 상어는 1번부터 시작
			st = new StringTokenizer(br.readLine(), " ");
			int r = stoi(st.nextToken());
			int c = stoi(st.nextToken());
			int s = stoi(st.nextToken());
			int d = stoi(st.nextToken())-1;
			int z = stoi(st.nextToken());
			map[r][c] = new Shark(i, s, d, z);
		}
		solve();
		System.out.println(total);
		
		br.close();
	}
	
	static void solve() {
		for(int playercol = 1; playercol<=C; playercol++) {
			// 1. 같은 열에서 땅과 가장 가까운 상어를 잡는다.
			getShark(playercol);
			// 2. 상어가 없어진다.
			map = sharkMove();
		}
	}
	
	// 1. 낚시꾼이 오른쪽 열로 움직이며, 
	//    같은 열에 있는 상어 중 땅에서 가장 가까운 상어를 잡는다.
	static void getShark(int playercol) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		
		for(int r = 1; r<=R; r++) {
			if(map[r][playercol]!=null) {
				Shark target = map[r][playercol];
				total += target.z;
				map[r][playercol] = null;
				return;
			} 
		}
	}
	
	// 2. 상어들이 움직인다.
	static Shark[][] sharkMove() {
		Shark[][] newMap = new Shark[R+1][C+1];
		
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j]==null) continue;
				
				Shark shark = map[i][j];
				
				int nr = i, nc = j;
				for(int k=1; k<=shark.s; k++) {
					nr += dr[shark.d];
					nc += dc[shark.d];
					
					// 이동 중간에 범위를 넘어가면 방향만 바꾼다.
					if(nr<1 || nr>R || nc<1 || nc>C) {
						nr -= dr[shark.d];
						nc -= dc[shark.d];
						
						shark.d = flipDir(shark.d);
	
						nr += dr[shark.d];
						nc += dc[shark.d];
					}

				}
				
				// 도착지
				if(newMap[nr][nc]!=null) {
					// 더 큰 상어만 남는다.
					if(newMap[nr][nc].z < shark.z) {
						newMap[nr][nc] = null;
						newMap[nr][nc] = new Shark(shark.idx, shark.s, shark.d, shark.z);
					}
				} else {
					newMap[nr][nc] = new Shark(shark.idx, shark.s, shark.d, shark.z);
				}
			}
		}
		
		return newMap;
	}	
	
	static int flipDir(int dir) {
		if(dir==0) {
			return 1;
		} else if(dir==1) {
			return 0;
		} else if(dir==2) {
			return 3;
		} else if(dir==3) {
			return 2;
		}		
		return 0;
	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
