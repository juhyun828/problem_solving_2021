import java.io.*;
import java.util.*;
// 210614

public class Main_BJ_11053_가장긴증가하는부분수열 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] dp = new int[10001][2];
        int N = stoi(br.readLine());
        int[] arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++) {
            arr[i] = stoi(st.nextToken());
        }

        arr[0] = 0;
        dp[0][0] = 0;
        dp[0][1] = 0;
        int max = 0;

        for(int i=1; i<=N; i++) {
            // i번째 택x
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);

            // i번째 택
            int lastLong = 0;
            for(int j=i-1; j>=0; j--) {
                if(arr[i] > arr[j]) {
                    lastLong = Math.max(lastLong, dp[j][1]);
                }
            }
            dp[i][1] = lastLong + 1;

            max = Math.max(max, dp[i][0]);
            max = Math.max(max, dp[i][1]);
            //System.out.println(i +", " + Arrays.toString(dp[i]));
        }

        System.out.println(max);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
