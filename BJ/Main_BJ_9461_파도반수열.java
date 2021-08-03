import java.io.*;
import java.util.*;
// 210611

public class Main_BJ_9461_파도반수열 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;

        for(int i=6; i<=100; i++) {
            dp[i] = dp[i-5] + dp[i-1];
        }

        int T = stoi(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            int num = stoi(br.readLine());
            System.out.println(dp[num]);
        }

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
