import java.io.*;
import java.util.*;
// 210820

public class Main_BJ_12865_평범한배낭 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = stoi(st.nextToken()); // 물품의 수
        int K = stoi(st.nextToken()); // 최대무게
        int[] w = new int[N+1]; // 무게
        int[] v = new int[N+1]; // 가치
        int[][] dp = new int[N+1][K+1]; // dp[i][k] : i번째 물건까지 고려, 무게 K일 때 최대 가치

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            w[i] = stoi(st.nextToken());// 물건의 무게
            v[i] = stoi(st.nextToken());// 물건의 가치
            dp[i][0] = 0; // 무게 K일 때 최대 가치는 0
        }

        for(int i=0; i<=K; i++) {
            dp[0][i] = 0; // 무게 0일 때 최대 가치는0
        }

        for(int i=1; i<=N; i++) { // i번째 물건
            for(int j=1; j<=K; j++){ // 무게 j
                if(j < w[i]) { // i번째 물건의 가치 무게[i]보다 적은 무게
                    dp[i][j] = dp[i-1][j];
                } else {
                    // max(이전 물건 유지, 이전 물건 빼고 + 이번 물건 넣기)
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - w[i]] + v[i]);
                }
            }
        }

        System.out.println(dp[N][K]);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
