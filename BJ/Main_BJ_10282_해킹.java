import java.io.*;
import java.util.*;
// 210707

public class Main_BJ_10282_해킹 {
    static int N, D, start;
    static ArrayList<Edge>[] adj;

    static class Edge implements Comparable<Edge> {
        int to, time;

        public Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = stoi(st.nextToken());
            D = stoi(st.nextToken());
            start = stoi(st.nextToken());
            adj = new ArrayList[N+1];
            for(int i=1; i<=N; i++) {
                adj[i] = new ArrayList<Edge>();
            }

            for(int i=0; i<D; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int to = stoi(st.nextToken());
                int from = stoi(st.nextToken());
                int w = stoi(st.nextToken());
                adj[from].add(new Edge(to, w));
            }

            int[] dist = dijkstra();
            int cnt = 0, time = 0;
            for(int i=1; i<=N; i++) {
                if(dist[i] < Integer.MAX_VALUE) {
                    ++cnt;
                    time = Math.max(time, dist[i]);
                }
            }

            sb.append(cnt + " " + time + "\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static int[] dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 시작점
        dist[start] = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            for(Edge next: adj[cur.to]) {
                if(dist[next.to] > cur.time + next.time) {
                    dist[next.to] = cur.time + next.time;
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }
        return dist;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
