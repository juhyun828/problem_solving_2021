import java.io.*;
import java.util.*;
// 210416

public class Main_BJ_17143_낚시왕 {
	static int R, C, M, total;
	static Shark[][] map; 
	static int[] dr = {-1, 1, 0, 0}; // 상 하 우 좌
	static int[] dc = {0, 0, 1, -1};
	
	static class Shark implements Comparable<Shark> {
		int idx;
		int r, c, s, d, z;
		
		public Shark(int idx, int r, int c, int s, int d, int z) {
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		// 땅과 가장 가까운 상어는 r이 가장 작은 상어
		@Override
		public int compareTo(Shark o) {
			return Integer.compare(this.r, o.r);
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
		
		int r, c, s, d, z;
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			r = stoi(st.nextToken());
			c = stoi(st.nextToken());
			s = stoi(st.nextToken());
			d = stoi(st.nextToken())-1;
			z = stoi(st.nextToken());
			
			map[r][c] = new Shark(i, r, c, s, d, z);
		}
		
		solve();
		
		// 낚시왕이 잡은 상어의 크기 합
		System.out.println(total);
		
		br.close();
	}
	
	// 1. 낚시꾼이 오른쪽 열로 움직이며, 같은 열에 있는 상어 중 땅에서 가장 가까운 상어를 잡는다.
	static void solve() {
		for(int i=1; i<=C; i++) {	
			ArrayList<Shark> list =new ArrayList<Shark>();
			for(int r=1; r<=R; r++) {
				if(map[r][i]!=null) {
					list.add(map[r][i]);
				}
			}
			
			if(!list.isEmpty()) {
				Collections.sort(list);
				// 잡는다.
				total += list.get(0).z;
				map[list.get(0).r][list.get(0).c] = null;
			}
			
			// 2. 상어들이 움직인다.
			map = move();
		} // for

	}
	
	// 2. 상어들이 움직인다.
	static Shark[][] move() {
		Shark[][] tmpMap = new Shark[R+1][C+1];
		
		int nr=0, nc=0;
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j]==null) continue;
				Shark cur = map[i][j];
				nr = cur.r; nc = cur.c;
				for(int k=1; k<=cur.s; k++) {
					nr += dr[cur.d];
					nc += dc[cur.d];
					
					// 이동 중간에 범위를 넘어가면 방향만 바꾼다.
					if(nr<1 || nr> R || nc<1 || nc>C) {
						nr -= dr[cur.d];
						nc -= dc[cur.d];
						
						cur.d = flipDir(cur.d);
						nr += dr[cur.d];
						nc += dc[cur.d];
					}
	
				}// nr, nc로
				
				// 도착지
				if(tmpMap[nr][nc]!=null) {
					if(tmpMap[nr][nc].z < cur.z) {
						// 이미 같은 칸에 상어가 있다면 큰 상어가 작은 상어를 잡아먹는다.
						tmpMap[nr][nc] = null;
						tmpMap[nr][nc] = new Shark(cur.idx, nr, nc, cur.s, cur.d, cur.z);
					}
				} else {
					tmpMap[nr][nc] = new Shark(cur.idx,nr, nc, cur.s, cur.d, cur.z);
				}					

			}// for
 		}
		
		return tmpMap;
	}
	
	static int flipDir(int d) {
		if(d==0) return 1;
		else if(d==1) return 0;
		else if(d==2) return 3;
		else if(d==3) return 2; 
		// 계속 답이 안나와서 고생했는데 여기서 리턴 값을 잘못줬었다. ㅜㅜ
		return 0;
	}

	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
