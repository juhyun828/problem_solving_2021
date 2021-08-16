import java.io.*;
import java.util.*;
// 210816

public class Main_BJ_2606_바이러스_union {
    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = stoi(br.readLine());
        M = stoi(br.readLine());
        int ans = 0;
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            union(from, to);
        }

        int startRoot = find(1);
        for(int i=2; i<=N; i++) {
            if(startRoot == find(i)) ++ans;
        }

        System.out.println(ans);
        br.close();
    }

    static int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x<y) {
            parents[y] = x;
        } else if(x > y) {
            parents[x] = y;
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}