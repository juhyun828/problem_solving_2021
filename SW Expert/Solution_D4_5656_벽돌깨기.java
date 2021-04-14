import java.io.*;
import java.util.*;
// 210414

public class Solution_D4_5656_벽돌깨기 {
	static int N, W, H;
	static int[][] map;
	static int[] order;
	static int min;
	static boolean[] somethingCol; // 구슬이 하나라도 있는 줄
	static int[][] newMap;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	static class Pos {
		int r, c, size;

		public Pos(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
		}
	}
	
	// 1. 뽑을 열 번호 고르기
	static void permutation(int cnt, int L) {
		if(cnt==N) {
			bomb();
			count();
			return;
		}
		
		//if(L==W) return;
		// W=3이어도, 같은 열을 여러 번 택하며 구슬을 4번 떨어트리는 경우도 있기에 리턴하면 안됨
		
		for(int i=0; i<W; i++) {
			if(!somethingCol[i]) continue;
			order[cnt] = i;
			permutation(cnt+1, L+1);
		}
	}
	
	// 2. 구슬 n번 떨어뜨리기
	static void bomb() {
		newMap = new int[H][W];
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		
		// 구슬 n번 떨어트리기
		for(int b=0; b<N; b++) {
			bfs(order[b]);
			down();
		}
	}
	
	// 2-1.
	static void bfs(int cidx) {
		Queue<Pos> q = new ArrayDeque<Pos>();
		
		// 위에서부터 부술 벽돌찾기
		for(int r=0; r<H; r++) {
			if(newMap[r][cidx]!=0) {
				if(newMap[r][cidx]==1) {
					newMap[r][cidx]=0;
					break;
				} else {
					q.offer(new Pos(r, cidx, newMap[r][cidx]-1));
					newMap[r][cidx]=0;
					break;
				}
					
			}	
		} /// 
		
		Pos cur; int nr, nc;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int k=1; k<=cur.size; k++) {
				for(int d=0; d<4; d++) {
					nr = cur.r + dr[d]*k;
					nc = cur.c + dc[d]*k;
					if(nr<0 || nr>=H || nc<0 || nc>=W || newMap[nr][nc]==0) continue;	
					
					if(newMap[nr][nc]==1) {
						newMap[nr][nc]=0;
					} else {
						q.offer(new Pos(nr, nc, newMap[nr][nc]-1));
						newMap[nr][nc]=0;		
					}
				}
			}
		}
	}
	
	// 3. 빈칸 아래로
	static void down() {
		ArrayList<Integer> cols;
		
		for(int c=0; c<W; c++) {
			cols = new ArrayList<Integer>();
			for(int r=0; r<H; r++) {
				if(newMap[r][c]!=0)
					cols.add(newMap[r][c]);
			} //
			// 교체
			int idx=H-1;

			for(int t=cols.size()-1; t>=0; t--) {
				newMap[idx--][c] = cols.get(t);
			}
			
			while(idx>=0) {
				newMap[idx--][c] = 0;
			}		
		} // 
	}
	
	// 4. 남은 벽돌 개수를 세며 최소값 갱신
	static void count() {
		int left=0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(newMap[i][j]!=0) ++left;
			}
		}
		min = Math.min(min, left);
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_D4_5656_벽돌깨기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = stoi(st.nextToken());
			W = stoi(st.nextToken());
			H = stoi(st.nextToken());
			order = new int[N];
			
			somethingCol = new boolean[W]; 
			// 구슬이 한 번도 없는 칸은 피해가려고 한건데 실행 시간이 크게 개선되지는 않는다.
			
			map = new int[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<W; j++) {
					map[i][j] = stoi(st.nextToken());
					if(map[i][j]!=0) somethingCol[j] = true;
				}
			}
			
			boolean all = false;
			for(int i=0; i<W; i++) {
				if(somethingCol[i]) {
					all = true;
					break;
				}
			}


			if(!all) {
				// 전부 다 빈칸이면
				int left=0;
				for(int i=0; i<H; i++) {
					for(int j=0; j<W; j++) {
						if(map[i][j]!=0) ++left;
					}
				}	
				sb.append("#" + tc + " " + left + "\n");
				continue;
			}
			
			min = Integer.MAX_VALUE;
			permutation(0, 0);
			sb.append("#" + tc + " " + min + "\n");
		} // tc
	
		System.out.println(sb.toString());
		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
