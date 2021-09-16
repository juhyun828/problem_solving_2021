import java.io.*;
import java.util.*;
// 210916

public class Main_BJ_11725_트리의부모찾기 {
    static int N, root;
    static ArrayList<Integer>[] adj;
    static int[] parents;
    static boolean[] v;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        root = 1;
        adj = new ArrayList[N+1];
        parents = new int[N+1];
        v = new boolean[N+1];

        for(int i=1; i<=N; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<N-1; i++) { // 정점이 N개일 때 간선 N-1 정보가 모두 주어짐
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = stoi(st.nextToken());
            int v = stoi(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        // root 노드 부터 탐색
        bfs(root);
        //dfs(root);

        for(int i=2; i<=N; i++) {
            if(!v[i]) bfs(i);
            //if(!v[i]) dfs(i);
        }

        for(int i=2; i<=N; i++) {
            System.out.println(parents[i]);
        }

        br.close();
    }

    static void bfs(int start) { // root부터 시작
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(start);
        v[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for(int next: adj[cur]) {
                if(!v[next]) { // root부터 탐색하기 때문에 아직 방문 안한 노드가 자식 노드다.
                    parents[next] = cur;
                    v[next] = true;
                    q.offer(next);
                }
            }
        }
    }

    static void dfs(int start) {
        v[start] = true;

        for(int next: adj[start]) {
            if(!v[next]) {
                parents[next] = start;
                dfs(next);
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}