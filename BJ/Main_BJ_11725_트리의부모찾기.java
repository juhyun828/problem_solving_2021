import java.io.*;
import java.util.*;
// 210909

public class Main_BJ_11725_트리의부모찾기 {
    static int N;
    static ArrayList<Integer>[] list;
    static int[] parents;
    static boolean[] v;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        
        // 인접 리스트
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<Integer>();
        }
        parents = new int[N+1];

        // 연결 설정
        StringTokenizer st;
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        v = new boolean[N+1];
        for(int i=1; i<=N; i++) {
            if(!v[i]) {
                //dfs(i);
                bfs(i);
            }
        }
        
        // 1이 최상단 루트
        for(int i=2; i<=N; i++) {
            System.out.println(parents[i]);
        }

        br.close();
    }

    static void bfs(int start) {
        // 시작점
        v[start] = true;
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for(int next: list[cur]) {
                if(!v[next]) {
                    v[next] = true;
                    parents[next] = cur;
                    q.offer(next);
                }
            }
        }
    }

    static void dfs(int cur) {
        if(v[cur]) return;
        v[cur] = true;

        for(int next: list[cur]) {
            if(!v[next]) {
                parents[next] = cur;
                dfs(next);
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}