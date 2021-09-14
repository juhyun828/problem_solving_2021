import java.io.*;
import java.util.*;
// 210913

public class Main_BJ_2644_촌수계산 {
    static int N, M;
    static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = stoi(st.nextToken());
        int to = stoi(st.nextToken());
        M = stoi(br.readLine());
        adj = new ArrayList[N+1];

        for(int i=1; i<=N; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
			// 왜 양방향으로 이어야하는지 이해를 못했는데 그림 그려보고 이해했다..
            adj[x].add(y);
            adj[y].add(x);
        }

        int level = bfs(from, to);
        System.out.println(level);

        br.close();
    }

    static int bfs(int from, int to) {
        boolean[] v = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<Integer>();
        // 시작점
        v[from] = true;
        q.offer(from);

        int level = 0;
        while (!q.isEmpty()) {
            ++level;
            int size = q.size();
            for(int i=0; i<size; i++) { // 큐의 size만큼 poll 하는 이유 : 상단 노드들 먼저 poll 된다.
                int cur = q.poll();
                for(int next: adj[cur]) {
                    if(v[next]) continue;
                    if(next == to) return level;
                    else {
                        v[next] = true;
                        q.offer(next);
                    }
                }
            }
        }

        return -1;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}