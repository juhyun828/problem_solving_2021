import java.io.*;
import java.util.*;
// 210613

public class Main_BJ_2156_포도주시식 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] dp = new int[10001][2];
        int N = stoi(br.readLine());
        int[] arr = new int[N+1];
        for(int i=1; i<=N; i++) {
            arr[i] = stoi(br.readLine());
        }

        dp[0][0] = 0;
        dp[0][1] = 0;
        int max = 0;
        for(int i=1; i<=N; i++) {

            if(i==1) {
                dp[i][0] = 0;
                dp[i][1] = arr[i];
                max = Math.max(max,  dp[i][0]);
                max = Math.max(max,  dp[i][1]);
                continue;
            }
            else if(i==2) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
                dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]) + arr[i];
                max = Math.max(max,  dp[i][0]);
                max = Math.max(max,  dp[i][1]);
                continue;
            }
            // i번째 택x
            // i-1 단계에서의 큰 값
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);

            // i번째 택
            //i-2단계 택x, i-1단계 택 or i-2단계 택, i-1단계 택x
            dp[i][1] = Math.max(dp[i-2][0] + arr[i-1], dp[i-2][1]) + arr[i];
            max = Math.max(max,  dp[i][0]);
            max = Math.max(max,  dp[i][1]);
        }

        System.out.println(max);

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
