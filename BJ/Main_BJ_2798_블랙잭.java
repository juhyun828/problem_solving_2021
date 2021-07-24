import java.io.*;
import java.util.*;
// 210724

public class Main_BJ_2798_블랙잭 {
    static int N, M, max;
    static int[] input;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        max = 0;

        st = new StringTokenizer(br.readLine(), " ");
        input = new int[N];
        for(int i=0; i<N; i++) {
            input[i] = stoi(st.nextToken());
        }

        dfs(0, 0, 0);
        System.out.println(max);

        br.close();
    }

    static void dfs(int cnt, int L, int total) {
        if(total > M) return;

        if(cnt==3) {
            if(total <= M) max = Math.max(max, total);
            return;
        }

        if(L==N) {
            return;
        }

        dfs(cnt+1, L+1, total + input[L]);
        dfs(cnt, L+1, total);
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}