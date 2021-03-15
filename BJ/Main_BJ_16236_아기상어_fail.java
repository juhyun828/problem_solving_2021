import java.io.*;
import java.util.*;
// 210315

// 상어와 사이즈가 같은 물고기 칸으로는 이동이 가능하지만 먹지는 못한다. 
// 이럴경우 이동이 가능한거 미구현

public class Main_BJ_16236_아기상어_fail {
	static int N;
	static int[][] map;
	static int cnt=0;
	static Shark shark;
	static ArrayList<Fish> fishList, smallerList;
	static int res;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static class Shark {
		int r, c, size=2, cnt=0;

		public Shark(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "상어 [r=" + r + ", c=" + c + ", size=" + size + ", cnt=" + cnt + "]";
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
				if(this.size==o.size) {
					if(this.r==o.r) {
						return this.c - o.c;
					}
					return this.r - o.r;
				}
				return this.size - o.size;
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
				f.dist = Math.abs(f.r - shark.r) + Math.abs(f.c - shark.c);
				smallerList.add(f);
			}
		}
		goEat();
	}
	
	static void goEat() {
		
		if(smallerList.size()==0) {
			return; // 먹을 고기 없음
		}
		
		Collections.sort(smallerList);
		
		// 맨 앞의 물고기를 먹는다.
		Fish front = smallerList.get(0);
		
		System.out.println(shark);
		System.out.println("먹을 물고기 " + front);
		
		map[front.r][front.c] = 0; // 먹고 없어짐
		shark.r = front.r; shark.c = front.c; // 이동
		res += front.dist;
		shark.cnt += 1;
		if(shark.cnt == shark.size) {
			shark.size += 1;
			shark.cnt += 0;
		}
		System.out.println("res " + res);
		findSmallerFish(); // 다음 물고기를 찾는다.
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_16236_아기상어.txt"));
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
		//System.out.println(fishList);
		System.out.println(res);
		
		br.close();
	} //
}
