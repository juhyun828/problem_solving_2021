import java.io.*;
import java.util.*;
// 210920

public class Main_BJ_13549_숨바꼭질3 {
    static int start, target, MAX, min;
    static int[] dist; // 위치 i에 도착하는 최소 시간은 dist[i]
    static boolean[] v;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        start = stoi(st.nextToken());
        target = stoi(st.nextToken());
        MAX = 100000;
        min = Integer.MAX_VALUE;

        v = new boolean[MAX+1];
        dist = new int[MAX+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

//        dikstra(start);
//        System.out.println(dist[target]);

        bfs();
        System.out.println(dist[target]);

        br.close();
    }

    static void bfs() {
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(start);
        v[start] = true;
        dist[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if(cur*2 <= MAX && !v[cur*2]) {
                v[cur*2] = true;
                dist[cur*2] = dist[cur];
                q.offer(cur*2);
            }

            if(cur-1>=0 && !v[cur-1]) {
                v[cur-1] = true;
                dist[cur-1] = dist[cur]+1;
                q.offer(cur-1);
            }

            if(cur+1<=MAX && !v[cur+1]) {
                v[cur+1] = true;
                dist[cur+1] = dist[cur]+1;
                q.offer(cur+1);
            }
        }
    }

    static void dikstra(int start) {
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if(cur*2 <= MAX && dist[cur*2] > dist[cur]) {
                dist[cur*2] = dist[cur];
                q.offer(cur*2);
            }

            if(cur-1>=0 && dist[cur-1] > dist[cur]+1) {
                dist[cur-1] = dist[cur]+1;
                q.offer(cur-1);
            }

            if(cur+1<=MAX && dist[cur+1] > dist[cur]+1) {
                dist[cur+1] = dist[cur]+1;
                q.offer(cur+1);
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}