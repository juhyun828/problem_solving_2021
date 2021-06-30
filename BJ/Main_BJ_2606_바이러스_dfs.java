import java.io.*;
import java.util.*;
// 210701

public class Main_BJ_2606_바이러스_dfs {
    static int N; // 정점 개수
    static int[][] map;
    static boolean[] v;
    static int cnt;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = stoi(br.readLine());
        int M = stoi(br.readLine());
        map = new int[N+1][N+1];
        v = new boolean[N+1];
        cnt = 0;

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            map[from][to] = 1;
            map[to][from] = 1;
        }
        v[1] = true;
        dfs(1);

        System.out.println(cnt);
        br.close();
    }

    static void dfs(int cur) {
        for(int i=1; i<=N; i++) {
            if(map[cur][i]==1 && !v[i]) {
                ++cnt;
                v[i] = true;
                dfs(i);
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

