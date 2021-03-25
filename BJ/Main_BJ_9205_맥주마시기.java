import java.io.*;
import java.util.*;
// 210325

public class Main_BJ_9205_맥주마시기 {
	static int N; 
	static boolean[] v;
	static Pos[] pArr; // 0 시작 N-1 마지막
	static Pos start, end;
	
	static boolean flag;
	static String ans;
	
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}

	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("res/input_BJ_9205_맥주마시기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = stoi(br.readLine());
			v = new boolean[N+2]; 
			pArr = new Pos[N+2];
			flag = false;
			
			int x, y;
			for(int i=0; i<N+2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x = stoi(st.nextToken());
				y = stoi(st.nextToken());
				pArr[i] = new Pos(x, y);
			} // for
			
			// 시작지
			start = pArr[0]; 
			// 도착지
			end = pArr[N+1];
			
			bfs();
			
			if(flag) ans = "happy";
			else ans = "sad";
			sb.append(ans + "\n");
			
		}
		
		System.out.println(sb.toString());
		br.close();
		
	}

	static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		boolean[] v = new boolean[N+2];
		
		// 시작점
		q.offer(start);
		v[0] = true;
		
		Pos cur, next;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			if(cur.equals(end)) {
				flag = true;
				return;
			}
			
			for(int i=1; i<pArr.length; i++) {
				if(v[i]) continue;
				
				next = pArr[i];
				if(go(cur, next)) {
					q.offer(next);
					v[i] = true;
				}
			}
		}
	}
	
	static boolean go(Pos from, Pos to) {
		// dist 계산
		int dist = Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
		
		// cnt 계산
		int cnt = dist / 50;
		if(dist%50!=0) cnt+=1;
		
		if ((20-cnt)<0) return false;
		else return true;
	}

}
