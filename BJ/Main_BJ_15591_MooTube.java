import java.io.*;
import java.util.*;
// 210704

public class Main_BJ_15591_MooTube {
    static int N, Q;
    static ArrayList<Node>[] adj;
    static class Node {
        int v, w; // 정점과 가중치
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        Q = stoi(st.nextToken());
        adj = new ArrayList[N+1]; // 1번 정점부터
        for(int i=1;i<=N; i++) {
            adj[i] = new ArrayList<Node>();
        }

        // N-1개의 간선 정보가 입력된다 -> MST
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int w = stoi(st.nextToken());
            adj[from].add(new Node(to, w));
            adj[to].add(new Node(from, w));
        }

        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int K = stoi(st.nextToken());
            int V = stoi(st.nextToken());
            // 정점 V에서 각 정점까지의 유사도가 K 이상인 정점들의 개수를 찾는다.
            int res = bfs(K, V);
            System.out.println(res);
        }

        br.close();
    }

    static int bfs(int K, int V) {
        int cnt = 0;
        int[] usado = new int[N+1];
        Arrays.fill(usado, Integer.MAX_VALUE);
        Queue<Node> q = new ArrayDeque<Node>();
        
        // 시작 정점 V
        for(Node next: adj[V]) {
            usado[next.v] = next.w;
            q.offer(new Node(next.v, usado[next.v]));
        }

        while(!q.isEmpty()) {
            Node cur = q.poll();

            for(Node next: adj[cur.v]) {
                if(next.v != V && usado[next.v] == Integer.MAX_VALUE) {
                    usado[next.v] = Math.min(cur.w, next.w);
                    q.offer(new Node(next.v, usado[next.v]));
                }
            }
        }

        for(int i=1; i<=N; i++) {
            if(i==V) continue;
            if(usado[i] >= K) ++cnt;
        }
        return cnt;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}