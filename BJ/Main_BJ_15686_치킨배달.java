import java.io.*;
import java.util.*;
// 210217

public class Main_BJ_15686_치킨배달 {
	static int N, M;
	static int[][] map;
	static int[] close;
	static ArrayList<Integer[]> chicken, house;
	static int disMin = Integer.MAX_VALUE;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static int distance() {
		int dist = 0;
		for(int i=0; i<house.size(); i++) {
			int r = house.get(i)[0];
			int c = house.get(i)[1];
			int min = Integer.MAX_VALUE;
			for(int j=0; j<chicken.size(); j++) {
				if(chicken.get(j)[2] == 1) { // 폐업x
					int nr = chicken.get(j)[0];
					int nc = chicken.get(j)[1];
					int tmp = Math.abs(nr-r) + Math.abs(nc-c);
					min = Math.min(min, tmp);
				}
			}
			dist += min;
		} 
		return dist;
	}
	
	static void close(int cnt, int L, int start) {
		// chicken.size()에서 폐업 시킬 전체-M개 고르기
		if(cnt == chicken.size()-M) {
			for(int i=0; i<close.length; i++) {
				chicken.get(close[i])[2] = 0; //폐업
			}
			disMin = Math.min(disMin, distance());
			for(int i=0; i<close.length; i++) {
				chicken.get(close[i])[2] = 1; // 원상복귀
			}
			return;
		}
		
		if (L == chicken.size()) return;
		
		close[cnt] = start;
		close(cnt+1, L+1, start+1);
		close(cnt, L+1, start+1);
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_15686_치킨배달.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		chicken =  new ArrayList<Integer[]>();
		house = new ArrayList<Integer[]>();
		
		map = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<N; c++) {
				map[r][c] = stoi(st.nextToken());
				if (map[r][c]==1) house.add(new Integer[] {r, c});
				if (map[r][c]==2) chicken.add(new Integer[] {r, c, 1});
			}
		}
		
		if (chicken.size()<=M) System.out.println(distance());
		else {
			close = new int[chicken.size()-M];
			close(0, 0, 0);
			System.out.println(disMin);
		}

		br.close();
	}

}
