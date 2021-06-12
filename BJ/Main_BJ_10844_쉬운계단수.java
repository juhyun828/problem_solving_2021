import java.io.*;
import java.util.*;
// 210612

public class Main_BJ_10844_쉬운계단수 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = stoi(br.readLine());

        long[][] dp = new long[101][10];
        int mod = 1000000000;
        for(int i=1; i<=9; i++) {
            dp[1][i] = 1;
        }

        for(int n=2; n<=num; n++) {
            for(int i=0; i<=9; i++) {
                if(i==0) dp[n][i] = dp[n-1][i+1]%mod;
                else if(i==9) dp[n][i] = dp[n-1][i-1]%mod;
                else dp[n][i] = (dp[n-1][i-1]+ dp[n-1][i+1])%mod;
            }
        }

        long res = 0;
        for(int i=0; i<=9; i++) {
            res += dp[num][i];
        }

        System.out.println(res%mod);

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
