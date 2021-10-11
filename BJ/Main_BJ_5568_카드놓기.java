import java.io.*;
import java.util.*;

public class Main_BJ_5568_카드놓기 {
    static int N, K, cnt;
    static int[] arr;
    static boolean[] v;
    static Set<String> set;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine());
        K = stoi(br.readLine());
        cnt = 0;
        set = new HashSet<String>();

        v = new boolean[N];
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = stoi(br.readLine());
        }

        permutation(0, 0, "");
        System.out.println(set.size());
        br.close();
    }

    static void permutation(int L, int R, String str) {
        if(R>=K) {
            set.add(str);
            return;
        }

        if(L>=N) return;

        for(int i=0; i<N; i++) {
            if(!v[i]) {
                v[i] = true;
                permutation(L+1, R+1, str+arr[i]);
                v[i] = false;
            }
        }

    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}