import java.io.*;
import java.util.*;
// 211010

public class Main_BJ_3584_가장가까운공통조상 {
    static ArrayList<Integer>[] tree;
    static int[] parents, depth;
    static int N; // 노드 수

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = stoi(br.readLine());

		for(int tc=1; tc<=T; tc++) {
			N = stoi(br.readLine());
			parents = new int[N+1];
			depth = new int[N+1];
			tree = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
			    tree[i] = new ArrayList<Integer>();
            }

			boolean[] rootCheck = new boolean[N+1]; // 체크되지 않는 노드가 root 노드
            Arrays.fill(rootCheck, true);
			for(int i=0; i<N-1; i++) {
				st = new StringTokenizer(br.readLine());
				int parent = stoi(st.nextToken());
				int child = stoi(st.nextToken());
				tree[parent].add(child);
                rootCheck[child] = false;
			}

			// 1. root 찾기
            int root = 0;
			for(int i=1; i<=N; i++) {
			    if(rootCheck[i]) {
			        root = i;
			        break;
                }
            }

            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());

            // 2. root부터 탐색하여 노드의 부모 노드와 깊이를 구한다.
            //getDepth(root, 1, 0);
            getDepthBfs(root);

            // 3. 두 x, y 노드 사이의 최소 공통 조상 구하기
            System.out.println(LCA(x, y));

		}
		br.close();
    }

    static void getDepth(int cur, int level, int parent) {
        depth[cur] = level;
        parents[cur] = parent;
        for(int next: tree[cur]) {
            if(next!=parent) {
                getDepth(next, level+1, cur);
            }
        }
    }

    static void getDepthBfs(int root) {
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(root);
        Arrays.fill(depth, -1);
        depth[root] = 0;
        parents[root] = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next: tree[cur]) {
                if(depth[next]==-1) {
                    depth[next] = depth[cur]+1;
                    parents[next] = cur;
                    q.offer(next);
                }
            }
        }
    }

    static int LCA(int x, int y) {
        int xDepth = depth[x];
        int yDepth = depth[y];

        // 3-1. 두 노드의 높이 맞추기
        while (xDepth > yDepth) {
            x = parents[x];
            xDepth--;
        }

        while (yDepth > xDepth) {
            y = parents[y];
            yDepth--;
        }

        // 3-2. 부모 노드가 일치할 때 까지 부모 노드 탐색 후 일치하면 출력
        while (x!=y) {
            x = parents[x];
            y = parents[y];
        }

        return x;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
