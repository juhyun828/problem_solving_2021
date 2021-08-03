import java.io.*;
import java.util.*;
// 210618

public class Main_BJ_12865_평범한배낭 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken()); // 최대무게
        int[] w = new int[N+1]; // 무게
        int[] v = new int[N+1]; // 가치
        int[][] dp = new int[N+1][K+1]; // dp[i][k] -> i번째 물건까지고려, 무게 k일 때의 최대 가치

        // 입력
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            w[i] = stoi(st.nextToken());
            v[i] = stoi(st.nextToken());
            dp[i][0] = 0;
        }
        for(int i=0; i<=K; i++) {
            dp[0][i] = 0;
        }

        for(int i=1; i<=N; i++) { // i번째 물건
            for(int k=1; k<=K; k++) { // 무게 k
                if(k < w[i]) {
                    dp[i][k] = dp[i-1][k]; // 이전 가치
                } else {
                    // max(이전 물건 유지, 이전 물건 빼고 + 이번 물건)
                    dp[i][k] = Math.max(dp[i-1][k], dp[i-1][k-w[i]] + v[i]);
                }
            }

            //System.out.println(Arrays.toString(dp[i]));
        }

        System.out.println(dp[N][K]);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

