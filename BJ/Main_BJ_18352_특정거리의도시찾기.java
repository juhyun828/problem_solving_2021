import java.io.*;
import java.util.*;
// 210826

public class Main_BJ_18352_특정거리의도시찾기 {
    static int N, M, K, X;
    static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken()); // 정점
        M = stoi(st.nextToken()); // 간선
        K = stoi(st.nextToken()); // 거리 정보
        X = stoi(st.nextToken()); // 출발 도시 번호
        adj = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<M; i++) { // 간선 정보
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            adj[from].add(to); // 단방향
        }

        int cnt = dijkstra(X);
        if(cnt == 0) System.out.println(-1);

        br.close();
    }

    static int dijkstra(int start) {
        int cnt = 0;
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Integer> q = new ArrayDeque<Integer>();
        // 시작점
        dist[start] = 0;
        q.offer(start);

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next: adj[cur]) {
                if(dist[next] > dist[cur] + 1) {
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                }
            }
        }

        for(int i=1; i<=N; i++) {
            if(dist[i] == K) {
                ++cnt;
                System.out.println(i);
            }
        }
        return cnt;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}