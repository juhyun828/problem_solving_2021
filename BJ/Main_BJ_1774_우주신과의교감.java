import java.io.*;
import java.util.*;
// 210826

public class Main_BJ_1774_우주신과의교감 {
    static int N, M;
    static int[] parents;
    static PriorityQueue<Edge> pq;
    static Pos[] posArr; // 좌표 정보

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to;
        double dist;

        public Edge(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = stoi(st.nextToken()); // 정점 개수
        M = stoi(st.nextToken()); // 이미 연결된 간선 개수
        posArr = new Pos[N+1];
        parents = new int[N+1];
        for(int i=1; i<=N; i++) {
            parents[i] = i;
        }

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            posArr[i] = new Pos(x, y);
        }

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            union(from, to);
        }

        pq = new PriorityQueue<Edge>(); // 거리 정보 저장
        for(int i=1; i<=N; i++) {
            for(int j=i+1; j<=N; j++) {
                double dist = calDist(i, j);
                pq.offer(new Edge(i, j, dist));
            }
        }

        double dist = kruskal();
        dist = dist * 100;
        dist = dist / 100.0;
        String str = String.format("%.2f", dist);
        System.out.println(str);

        br.close();
    }

    static double kruskal() {
        // 이미 M개의 간선이 연결되어 있으니
        // N-1-M개의 간선을 더 택한다.
        double total = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            int fromRoot = find(cur.from);
            int toRoot = find(cur.to);
            if(fromRoot == toRoot) continue;
            // 연결이 되지 않는 간선이면 연결
            union(cur.from, cur.to);
            total += cur.dist;
            ++cnt;
            if(cnt == N-1-M) break;
        }

        return total;
    }

    static int find(int x) {
        if(parents[x]==x) return x;
        else return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x < y) {
            parents[y] = x;
        } else if(x > y) {
            parents[x] = y;
        }
    }

    static double calDist(int from, int to) {
        int x1 = posArr[from].x;
        int y1 = posArr[from].y;
        int x2 = posArr[to].x;
        int y2 = posArr[to].y;
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
//        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}