import java.io.*;
import java.util.*;
// 210918

public class Main_BJ_17073_나무위의빗물 {
    static int N, W;
    static ArrayList<Integer>[] adj;
    static boolean[] v;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        W = stoi(st.nextToken());
        int root = 1;
        adj = new ArrayList[N+1];
        v = new boolean[N+1];
        for(int i=1; i<=N; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = stoi(st.nextToken());
            int v = stoi(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        // root부터 방문하며 부모 노드를 찾는다.
        int leaf = bfs(root);
        if(leaf==0) {
            System.out.println((double)W);
        } else {
            System.out.println((double)W/leaf);
        }
        
        br.close();
    }

    static int bfs(int start) {
        Queue<Integer> q = new ArrayDeque<Integer>();
        v = new boolean[N+1];
        v[start] = true;
        q.offer(start);
        int cnt = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            boolean notLeaf = false;
            for(int next: adj[cur]) {
                if(!v[next]) {
                    notLeaf = true;
                    v[next] = true;
                    q.offer(next);
                }
            }
            if (!notLeaf) ++cnt;
        }

        return cnt;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}