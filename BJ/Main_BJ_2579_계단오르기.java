import java.io.*;
import java.util.*;
// 210612

public class Main_BJ_2579_계단오르기 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] arr = new int[N+1];
        int[][] dp = new int[N+1][2];

        for(int i=1; i<=N; i++) {
            arr[i] = stoi(br.readLine());
        }

        dp[0][0] = 0;
        dp[0][1] = 0;

        for(int i=1; i<=N; i++) {
            if(i==1) {
                dp[i][0] = dp[i-1][0] + arr[i];
                continue;
            }
            if(i==2) {
                dp[i][0] = dp[i-1][0] + arr[i];
                dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + arr[i];
                continue;
            }
            
            // 이전칸 + 한칸 -> 이전칸을 이이전칸에서 두칸으로 올라옴
            dp[i][0] = dp[i-1][1] + arr[i];

            // 이이전칸 + 두칸 -> 이이전칸에서 한칸으로 올라오든 두칸으로 올라오든 상관x
            dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + arr[i];
        }

        int max = Math.max(dp[N][0], dp[N][1]);
        System.out.println(max);

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
