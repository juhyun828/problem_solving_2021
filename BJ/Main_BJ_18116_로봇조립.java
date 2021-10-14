import java.io.*;
import java.util.*;

public class Main_BJ_18116_로봇조립 {
    static int N;
    static int[] parents, cnt;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = stoi(br.readLine());
        parents = new int[1000001];
        cnt = new int[1000001];
        for(int i=1; i<=1000000; i++) {
            parents[i] = i;
            cnt[i] = 1;
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            if(st.nextToken().equals("I")) {
                int x = stoi(st.nextToken());
                int y = stoi(st.nextToken());
                if(x!=y) union(x, y);
            } else {
                int x = stoi(st.nextToken());
                sb.append(cnt[find(x)] + "\n");
            }
        }

        System.out.println(sb.toString());
        br.close();
    }

    static int find(int x) {
        if(parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x<y) {
            parents[y] = x;
            cnt[x] += cnt[y];
            cnt[y] = 0;
        } else if(x>y) {
            parents[x] = y;
            cnt[y] += cnt[x];
            cnt[x] = 0;
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}