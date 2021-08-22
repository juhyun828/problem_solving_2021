import java.io.*;
import java.util.*;
// 210822

public class Main_BJ_15565_귀여운라이언_fail {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken());
        int[] arr = new int[N+1];
        int min = Integer.MAX_VALUE;
        int[][] dp = new int[N+1][N+1];
        dp[0][0] = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++) {
            arr[i] = stoi(st.nextToken());
            Arrays.fill(dp[i], 0);
            dp[0][i] = 0;
        }

        for(int i=1; i<=N; i++) {
            for(int j=i; j<=N; j++) {
                if(arr[j]==1) {
                    dp[i][j] = dp[i][j-1] + 1;
                } else {
                    dp[i][j] = dp[i][j-1];
                }

                if(dp[i][j] >= K) {
                    min = Math.min(min, j-i+1);
                }
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}