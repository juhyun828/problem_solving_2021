import java.io.*;
import java.util.*;
// 211016

public class Main_BJ_16987_계란으로계란치기 {
    static int[] durability, weight;
    static int N, max;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine());
        durability = new int[N];
        weight = new int[N];
        max = 0;

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            durability[i] = stoi(st.nextToken());
            weight[i] = stoi(st.nextToken());
        }

        dfs(0);

        System.out.println(max);
        br.close();
    }

    static void dfs(int L) {
        if(L>=N) {
            int cnt = 0;
            for(int i=0; i<N; i++) {
                if(durability[i]<=0) ++cnt;
            }
            max = Math.max(max, cnt);
            return;
        }

        if(durability[L] <= 0) dfs(L+1);
        else {
            boolean flag = false;
            for(int i=0; i<N; i++) {
                if(i==L) continue;
                if(durability[i]>0) { // 깨지지 않음
                    flag = true;
                    int beforeNextDurability = durability[i];
                    int beforeThisDurability = durability[L];

                    durability[i] -= weight[L];
                    durability[L] -= weight[i];

                    dfs(L+1);

                    durability[i] = beforeNextDurability;
                    durability[L] = beforeThisDurability;
                }
            }
            if(!flag) dfs(L+1);
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}