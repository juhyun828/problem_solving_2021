import java.io.*;
import java.util.*;
// 210423

public class Main_BJ_16235_나무재테크 {
	static int N, M, K;
	static int[][] map, A;
	static ArrayList<Integer>[][] tree;
	static ArrayList<int[]> death; // r, c, 나이 순
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1 , -1, 1, -1, 0, 1};
	
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_BJ_16235_나무재테크.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		map = new int[N+1][N+1]; // 현재 양분 상태
		for(int i=1; i<=N; i++) {
			Arrays.fill(map[i], 5);
		}
		tree = new ArrayList[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				tree[i][j] = new ArrayList<Integer>();
			}
		}
		
		A = new int[N+1][N+1]; // 겨울에 더할 양분
		// A 입력
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=N; j++) {
				A[i][j] = stoi(st.nextToken());
			}
		}
		
		// 나무 입력
		int r, c, age;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			r = stoi(st.nextToken());
			c = stoi(st.nextToken());
			age = stoi(st.nextToken());
			tree[r][c].add(age);
		}
		
		solve();
		br.close();
	}
	
	static void solve() {
		for(int i=0; i<K; i++) {
			death = new ArrayList<int[]>();
			tree = spring();
			summer();
			autumn();
			winter();
		}
		
		// 남은 나무 개수 출력
		System.out.println(count());
	}
	
	static int count() {
		int left=0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				left += tree[i][j].size();
			}
		}
		return left;
	}
	
	static ArrayList<Integer>[][] spring() {
		ArrayList<Integer>[][] newTree = new ArrayList[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				newTree[i][j] = new ArrayList<Integer>();
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(tree[i][j].size()==0) continue;
				
				Collections.sort(tree[i][j]);
				
				// 나이 어린 순서대로 양분을 먹는다.
				for(int z=0; z<tree[i][j].size(); z++) {
					int age = tree[i][j].get(z);
					if(map[i][j] - age < 0) {
						// 양분이 모자르면 죽는다.
						death.add(new int[] {i, j, age});
					} else {
						// 나이만큼 양분을 먹는다.
						map[i][j] -= age;
						newTree[i][j].add(age+1);
					}
				}
			}
		}
		
		return newTree;
	}

	static void summer() {
		// 죽은 나무들이 양분이 된다.
		if(death.size()==0) return;
		
		for(int i=0; i<death.size(); i++) {
			int[] deadTree = death.get(i);
			int amount = deadTree[2]/2;
			map[deadTree[0]][deadTree[1]] += amount;
		}
	}
	
	static void autumn() {
		// 8방향 번식
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(tree[i][j].size()==0) continue;

				for(int z=0; z<tree[i][j].size(); z++) {
					// 나이가 5의 배수일 때만 번식함
					if(tree[i][j].get(z) % 5 !=0) continue;
					
					for(int d=0; d<8; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(nr<1 || nr>N || nc<1 || nc>N) continue;
						tree[nr][nc].add(1);
					}
				}
			}
		}
	}
	
	static void winter() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
