import java.io.*;
import java.util.*;
// 211013

public class Main_BJ_16562_친구비 {
    static int N, M, K;
    static int[] costs, parents;
    static boolean[] v;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        int ans = 0;

        costs = new int[N+1];
        parents = new int[N+1];
        for(int i=1; i<=N; i++) {
            parents[i] = i;
        }
        v = new boolean[N+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++) {
            costs[i] = stoi(st.nextToken());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = stoi(st.nextToken());
            int v = stoi(st.nextToken());
            union(u, v);
        }

        for(int i=1; i<=N; i++) {
            int iRoot = find(i);
            if(v[iRoot]) continue;
            ans += costs[iRoot];
            v[iRoot] = true;
            if (ans > K) {
                System.out.println("Oh no");
                return;
            }
        }

        System.out.println(ans);
        br.close();
    }

    static int find(int x) {
        if(parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        // 더 적은 비용이 부모 노드로
        if(costs[x] < costs[y]) {
            parents[y] = x;
            costs[y] = costs[x];
        } else {
            parents[x] = y;
            costs[x] = costs[y];
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
