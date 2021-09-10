import java.io.*;
import java.util.*;
// 210804

public class Main_BJ_1325_효율적인해킹_fail {
    static int N, M, max;
    static ArrayList<Integer>[] map;
    static ArrayList<Integer> list;
    public static void main(String[] args) throws Exception {
       // System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            map[i] = new ArrayList<Integer>();
        }
        list = new ArrayList<Integer>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int to = stoi(st.nextToken());
            int from = stoi(st.nextToken());
            map[from].add(to);
        }

        for(int i=1; i<=N; i++) {
            int cnt = bfs(i);
            list.add(cnt);
        }

        for(int i=1; i<=N; i++) {
            if(max==list.get(i-1)) sb.append(i + " ");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static int bfs(int start) {
        int cnt = 1;
        boolean[] v = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        v[start] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int i: map[cur]) {
                if(!v[i]) {
                    q.offer(i);
                    ++cnt;
                }
            }
        }

        max = Math.max(max, cnt);
        return cnt;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}