import java.io.*;
import java.util.*;
// 211022

public class Main_BJ_10971_외판원순회2 {
    static int N, min;
    static int[][] map;
    static boolean[] v;
	
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        min = Integer.MAX_VALUE;
        N = stoi(br.readLine());
        map = new int[N][N];
        v = new boolean[N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            dfs(i, i, 0, 0);
        }

        System.out.println(min);

        br.close();
    }

    static void dfs(int start, int last, int cnt, int cost) {
        if(cost>min) return;
        if(cnt>0 && cnt<N && start==last) return;
        if(cnt==N) {
            if(start==last) {
                min = Math.min(min, cost);
            }
            return;
        }

        for(int i=0; i<N; i++) {
            if(!v[i] && map[last][i]>0) {
                v[i] = true;
                dfs(start, i, cnt+1, cost+map[last][i]);
                v[i] = false;
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}