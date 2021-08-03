import java.io.*;
import java.util.*;
// 210305

public class Main_BJ_17406_배열돌리기4_2 {
	
	static int N, M, K, min, res[];
	static boolean[] visited;
	static int[][] map, cloneMap;
	static Command[] commands;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static class Command {
		int r, c, s;

		public Command(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}

		@Override
		public String toString() {
			return "Command [r=" + r + ", c=" + c + ", s=" + s + "]";
		}
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void permutation(int cnt) {
		if(cnt==K) {
			copy();
			for(int i=0; i<K; i++) {
				int cIdx = res[i];
				rotate(commands[cIdx]);
			}
			min = Math.min(min, calArr());
			return;
		}
		for(int i=0; i<K; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			res[cnt] = i; 
			permutation(cnt+1);
			visited[i] = false;
		}
	}
	
	static void printMap(int[][] iMap) {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(iMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-----------");
	}
	
	static int calArr() {
		int curMin = Integer.MAX_VALUE;
		for(int i=1; i<=N; i++) {
			int sum = 0;
			for(int j=1; j<=M; j++) {
				sum += cloneMap[i][j];
			}
			curMin = Math.min(curMin, sum);
		}
		return curMin;
	}
	
	static void rotate(Command cm) {
		int r = cm.r, c = cm.c, s = cm.s;
		for(int i=0; i<s; i++) {
			int startR = r-s+i, startC = c-s+i;
			int endR = r+s-i, endC = c+s-i;
			int or = startR, oc = startC;
			int start = cloneMap[or][oc];
			int d=0;
			
			while(true) {
				int nr = or + dr[d];
				int nc = oc + dc[d];
				
				if(nr<startR || nr>endR || nc<startC || nc>endC) {
					++d;
					continue;
				} 
				
				if(or==startR && oc==(startC+1)) {
					cloneMap[or][oc] = start;
					break;
				} else {
					cloneMap[or][oc] = cloneMap[nr][nc];
					or = nr; oc = nc;
				}
			}
//			printMap(cloneMap);
		} // while

	}
	
	static void copy() {
		for(int i=1; i<=N; i++) {
			System.arraycopy(map[i], 1, cloneMap[i], 1, M);
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_17406_배열돌리기4.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		min = Integer.MAX_VALUE;
		map = new int[N+1][M+1];
		cloneMap = new int[N+1][M+1];
		res = new int[K]; visited = new boolean[K];
		commands = new Command[K]; 
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			commands[i] = new Command(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
		}
		
		permutation(0);
		System.out.println(min);
		
		br.close();
	}
}
