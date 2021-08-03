import java.io.*;
import java.util.*;
// 210612

public class Main_BJ_1932_정수삼각형_2 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] dp = new int[501][501];
        int N = stoi(br.readLine());
        int[][] map = new int[N+1][N+1];

        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=i; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        // dp 테이블
        dp[0][0] = 0;

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=i; j++) {
                if(j==1) dp[i][j] = map[i][j] + dp[i-1][j];
                else dp[i][j] = map[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
            }
        }

        int max = 0;
        for(int j=1; j<=N; j++) {
            if(max < dp[N][j]) max = dp[N][j];
        }

        System.out.println(max);

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
