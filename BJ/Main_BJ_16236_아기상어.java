import java.io.*;
import java.util.*;
// 210317

public class Main_BJ_16236_아기상어 {
	static int N;
	static int[][] map;
	static Shark shark;
	static ArrayList<Fish> fishList, smallerList;
	static int res;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Shark {
		int r, c, size=2, cnt=0;

		public Shark(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", size=" + size + ", cnt=" + cnt + "]";
		}

	}
	
	static class Fish implements Comparable<Fish> {
		int r, c, size, dist;

		public Fish(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
		}
		
		@Override
		public int compareTo(Fish o) {
			if(this.dist==o.dist) {
				if(this.r==o.r) {
					return this.c - o.c;
				}
				return this.r - o.r;
			}
			return this.dist - o.dist;
			
		}

		@Override
		public String toString() {
			return "Fish [r=" + r + ", c=" + c + ", size=" + size + ", dist=" + dist +"]";
		}
		
	}
	
	static void findSmallerFish() {
		smallerList = new ArrayList<>();
		
		for(Fish f: fishList) {
			// 살아있고 현재 상어보다 크기가 작다면 추가한다.
			if(map[f.r][f.c]>0 && f.size<shark.size) {
				smallerList.add(f);
			}
		}
		goEat();
	}
	
	static void goEat() {
		
		if(smallerList.size()==0) {
			return; // 먹을 고기 없음
		}
		
		for(Fish f: smallerList) {
			f.dist = bfs(shark, f);
			// 중간에 이동할 수 없는 경우를 고려하여 거리 계산
		}
		
		Collections.sort(smallerList);
		
		boolean flag = false;
		for(Fish f: smallerList) {
			if(f.dist==0) continue; // 거리가 0면 이동할 수 없다.
			
			res += f.dist; // 이동
			map[f.r][f.c] = 0; // 물고기가 먹혀서 없어짐
			map[shark.r][shark.c] = 0; // 상어가 떠남
			shark.r = f.r; shark.c = f.c; // 상어 이동
			shark.cnt += 1;
			if(shark.cnt == shark.size) {
				shark.size += 1;
				shark.cnt = 0;
			}
			flag=true;
			break;
			
		}
		
		if(!flag) return; // 거리 안에 먹을 수 있는 물고기가 없으면 종료
		else findSmallerFish(); // 다음 물고기를 찾는다.
	
	}

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int bfs(Shark s, Fish f) {
		
		int[][] visited = new int[N][N];
		Queue<Pos> q = new ArrayDeque<>();
		
		// visited[s.r][s.c] = 1;
		q.offer(new Pos(s.r, s.c));
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N || map[nr][nc]>s.size) continue;
				if(nr==s.r && nc==s.c) continue;
				
				if(visited[nr][nc]>0) {// 이미 방문 했는데
					if(visited[nr][nc] > visited[p.r][p.c] + 1) { // 더 적은 수로 방문할 수 있을 때
						visited[nr][nc] = visited[p.r][p.c] + 1;
						q.offer(new Pos(nr, nc));
					} 
				} else  {
					visited[nr][nc] = visited[p.r][p.c] + 1;
					q.offer(new Pos(nr, nc));
				}
			
			}
		}
		return visited[f.r][f.c];
		
	}
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_16236_아기상어.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = stoi(br.readLine());
		map = new int[N][N];
		fishList = new ArrayList<>();
		res = 0;

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = stoi(st.nextToken());
				if (map[i][j]==9) {
					shark = new Shark(i, j);
				} else if (map[i][j]>0) {
					fishList.add(new Fish(i, j, map[i][j]));
				}
			}
		}
		
		findSmallerFish();
		System.out.println(res);
		
		br.close();
	} //
}
