import java.io.*;
import java.util.*;
// 210823

public class Main_BJ_12896_스크루지민호_fail {
    static int N;
    static ArrayList<Integer>[] adj;
    static int min;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = stoi(br.readLine());
        adj = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            adj[from].add(to);
            adj[to].add(from);
        }

        min = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++) {
            bfs(i);
        }

        System.out.println(min);

        br.close();
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<Integer>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int max = 0;
        // 시작점
        q.offer(start);
        dist[start] = 0;

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next: adj[cur]) {
                if(dist[next] > dist[cur] + 1) {
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                    max = Math.max(max, dist[next]);
                    if(max > min) return;
                }
            }
        }

        min = Math.min(min, max);
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}