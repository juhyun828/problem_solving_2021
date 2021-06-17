import java.io.*;
import java.util.*;
// 210617

public class Main_BJ_9251_LCS {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] dp = new int[1001][1001];
        char[] arr1  = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();

        int max = 0;
        for(int i=1; i<= arr1.length; i++) {
            for(int j=1; j<=arr2.length; j++) {
                // arr1[i-1] arr2[j-1] -> dp[i][j]
                if(arr1[i-1] == arr2[j-1]) {
                    // 이전의 LCS + 1
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(max);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
