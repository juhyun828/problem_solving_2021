import java.io.*;
import java.util.*;
// 210616

public class Main_BJ_1912_연속합 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] arr = new int[N+1];
        int[] dp = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++) {
            arr[i] = stoi(st.nextToken());
        }

        int max = Integer.MIN_VALUE;
        dp[0] = 0;
        for(int i=1; i<=N; i++) {
            dp[i] = Math.max(arr[i], dp[i-1]+arr[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
