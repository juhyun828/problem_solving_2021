import java.io.*;
import java.util.*;
// 210701

public class Main_BJ_9095_123더하기 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        int[] dp = new int[12];  // n<=11
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i=4; i<=11; i++) {
            dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
        }

        for(int tc=1; tc<=T; tc++) {
            int n = stoi(br.readLine());
            System.out.println(dp[n]);
        }
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}