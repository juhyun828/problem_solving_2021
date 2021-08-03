import java.io.*;
import java.util.*;
// 210419

public class Main_BJ_15686_치킨배달 {
	static int N, M, minDist; // 치킨집 M개 남기고 폐업
	static int[][] map; // 0: 빈칸, 1: 집, 2: 치킨집
	static boolean[] close; // 폐점시킬 치킨집
	static ArrayList<Pos> chicken, house;
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_15686_치킨배달.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		minDist = Integer.MAX_VALUE;
		chicken = new ArrayList<Pos>();
		house = new ArrayList<Pos>();
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = stoi(st.nextToken());
				if(map[i][j]==1) house.add(new Pos(i, j));
				if(map[i][j]==2) chicken.add(new Pos(i, j));
			}
		}
		
		close = new boolean[chicken.size()];
		if(chicken.size()==M) getDist();
		else {
			comb(0, 0);
		}
 
		System.out.println(minDist);
		br.close();
	}
	
	// 1. 폐업시킬 치킨집 고르기
	static void comb(int cnt, int L) { 
		// M개를 폐업시키는게 아니라 M개만 살려놓고 나머지를 폐업시킨다.
		if(cnt==chicken.size()-M) {
			getDist();
			return;
		}
		
		if(L==chicken.size()) return;
		
		close[L] = true;
		comb(cnt+1, L+1);
		
		close[L] = false;
		comb(cnt, L+1);
	}
	
	// 2. 폐업되지 않은 치킨집과 집들간의 치킨거리 구하기
	static void getDist() {
		int total = 0;
		
		for(int i=0; i<house.size(); i++) {
			Pos h = house.get(i);
			
			int min = Integer.MAX_VALUE;
			for(int j=0; j<chicken.size(); j++) {
				if(close[j]) continue;
				Pos c = chicken.get(j);
				min = Math.min(min, cal(h.r, h.c, c.r, c.c));
			}
			total += min;
			if(total > minDist) return;
		} 
		minDist = Math.min(minDist, total);
	}
	
	static int cal(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2) + Math.abs(c1-c2);
	}
 
	static int stoi(String str) {
		return Integer.parseInt(str);
	}

}