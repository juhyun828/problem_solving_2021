import java.io.*;
import java.util.*;
// 210918

public class Main_BJ_1325_효율적인해킹 {
    static int N, M;
    // static ArrayList<Integer>[] adj;
    // 2차원 배열로 구현 시 메모리 낭비 발생, ArrayList로 필요한 공간만 사용하여 시간 초과 해결
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] v;
    static int[] possible;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        possible = new int[N+1];
        v = new boolean[N+1];
        adj = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<=N; i++) {
            adj.add(new ArrayList<Integer>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int to = stoi(st.nextToken());
            int from = stoi(st.nextToken());
            adj.get(from).add(to);
        }

        int max = 0;
        for(int i=1; i<=N; i++) {
            int cnt = bfs(i);
            max = Math.max(max, cnt);
            possible[i] = cnt;
        }

        for(int i=1; i<=N; i++) {
            max = Math.max(max, possible[i]);
        }

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
            if(!v[cur]) continue;

            for(int next: adj.get(cur)) {
                if(!v[next]) {
                    v[next] = true;
                    ++cnt;
                    q.offer(next);
                }
            }
        }
        return cnt;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}