import java.io.*;
import java.util.*;
// 210916

public class Main_BJ_1325_효율적인해킹 {
    static int N, M;
    static ArrayList<Integer>[] adj;
    static int[] possible;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        possible = new int[N+1];
        int max = 0;
        v = new boolean[N+1];
        adj = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int to = stoi(st.nextToken());
            int from = stoi(st.nextToken());
            adj[from].add(to);
        }

        // bfs
        for(int i=1; i<=N; i++) {
            int cnt = bfs(i);
            max = Math.max(max, cnt);
            possible[i] = cnt;
        }

        // dfs -> 데이터 추가되고 재채점 되면서 dfs로 푼건 시간 초과 난다. 
//        for(int i=1; i<=N; i++) {
//            v = new boolean[N+1];
//            dfs(i, i);
//            max = Math.max(max, possible[i]);
//        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            if(possible[i] == max) sb.append(i + " ");
        }

        System.out.println(sb.toString().trim());
        br.close();
    }

    static int bfs(int start) {
        int cnt = 1;
        v = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<Integer>();
        v[start] = true;
        q.offer(start);

        while(!q.isEmpty()) {
            int cur = q.poll();
            // 순환을 이루는 경우, 큐에 들어간 이후에 방문 처리 될 수 있다.
            if(!v[cur]) continue;

            for(int next: adj[cur]) {
                if(!v[next]) {
                    v[next] = true;
                    ++cnt;
                    q.offer(next);
                }
            }
        }
        return cnt;
    }

    static void dfs(int start, int now) {
        possible[start]++;
        v[now] = true;

        for(int next: adj[now]) {
            if(!v[next]) {
                dfs(start, next);
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}