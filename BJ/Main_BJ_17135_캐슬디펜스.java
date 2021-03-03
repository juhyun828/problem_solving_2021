import java.io.*;
import java.util.*;
// 210303

public class Main_BJ_17135_캐슬디펜스 {
	static class Enemy implements Comparable<Enemy>{
		int idx;
		int nr;
		int c;
		int r;
		int alive; // 1이면 살아있음
		int d; // 궁수와의 거리
		
		public Enemy(int idx, int r, int c) {
			this.idx = idx;
			this.nr = r;
			this.c = c;
			this.r = r;
			this.alive = 1; // 초기값은 항상 살아있어야
		}
		
		@Override
		public int compareTo(Enemy o) {
			if(this.d==o.d) return this.c-o.c; // 거리 같으면 열 오름차순 (작은것부터)
			else return this.d-o.d; // 거리 작은 순부터
		}

		@Override
		public String toString() {
			return "Enemy [idx=" + idx + ", nr=" + nr + ", c=" + c + ", r=" + r + ", alive=" + alive + ", d=" + d + "]";
		}

	}
	
	static class Archer {
		int r;
		int c;
		public Archer(int c) {
			this.r=N;
			this.c=c;
		}
		@Override
		public String toString() {
			return "Archer [r=" + r + ", c=" + c + "]";
		}
	}

	static int N, M, D, max, enemyCnt, killCnt, deathCnt;
	static int[][] map;
	static ArrayList<Enemy> enemys;
	
	static int[] archerC = new int[3];
	static void combination(int cnt, int start) {
		if(cnt==3) {
			ArrayList<Archer> archers = new ArrayList<>();
			for(int i=0; i<3; i++) {
				archers.add(new Archer(archerC[i]));
			}

			max = Math.max(max, shoot(archers));
			setAlive();
			return;
		}
		
		for(int i=start; i<M; i++) {
			archerC[cnt] = i;
			combination(cnt+1, i+1);
		}
		
	} //
	
	static int shoot(ArrayList<Archer> archers) {
		killCnt = 0; // 궁수가 죽이는 적
		deathCnt = 0; // 전체적으로 죽이는 적

		ArrayList<Enemy> targetd = new ArrayList<>(); // 궁수가 죽일 적 (최종)
		while(true) {
			if(deathCnt>=enemyCnt) {
				break;
			}
			for(int ai=0; ai<3; ai++) { // 궁수는 3명
				ArrayList<Enemy> inDistance = new ArrayList<>();
				for(int i=0; i<enemyCnt; i++) {
					Enemy e = enemys.get(i);

					// 죽일 수 있는지 확인 (거리 안이고, alive=1)
					int tmpDist = cal(archers.get(ai).r, archers.get(ai).c, e.nr, e.c);
					if(tmpDist<=D && e.alive==1) {
						e.d = tmpDist;
						inDistance.add(e);
					}
				} // 
				Collections.sort(inDistance);
				if(inDistance.size()>0) {
					Enemy minDist = inDistance.get(0);
					targetd.add(minDist);
				}
			}
			
			// 3명의 궁수에 대한 검사가 끝났으면 죽인다.
			kill(targetd);
			down();
		} // while
		return killCnt;
	}
	
	static void kill(ArrayList<Enemy> targetd) {
		for(int i=0; i<targetd.size(); i++) { 
			// 살아있어야만 죽일 수 있다.
			if(targetd.get(i).alive==1) {
				++killCnt; ++deathCnt;
				targetd.get(i).alive=0;
				enemys.get(targetd.get(i).idx).alive=0;
			}
		}
		
	}
	
	static void setAlive() {
		for(int i=0; i<enemyCnt; i++) {
			enemys.get(i).nr = enemys.get(i).r;
			enemys.get(i).alive = 1;
		}
	}
	
	static void down() {
		for(int i=0; i<enemys.size(); i++) {
			if(enemys.get(i).alive==1) {
				enemys.get(i).nr += 1;;
				if(enemys.get(i).nr>=N) {
					enemys.get(i).alive=0;
					++deathCnt;
				}
			}
		}
	}
	
	static int cal(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2) + Math.abs(c1-c2);
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_BJ_17135_캐슬디펜스.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken()); // 행의 수
		M = stoi(st.nextToken()); // 열의 수
		D = stoi(st.nextToken()); // 궁수의 공격 거리 제한
		max = 0; enemyCnt = 0;
		
		map = new int[N][M];
		
		enemys = new ArrayList<>();
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<M; c++) {
				map[r][c] = stoi(st.nextToken());
				if(map[r][c]==1) {
					enemys.add(new Enemy(enemyCnt, r, c));
					++enemyCnt;
				}
				
			}
		}
		combination(0, 0);
		System.out.println(max);
		br.close();
	} // main
	
}
