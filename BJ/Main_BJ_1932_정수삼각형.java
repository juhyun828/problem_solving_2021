import java.io.*;
import java.util.*;
// 210606

public class Main_BJ_1932_정수삼각형 {
    static int N;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = stoi(br.readLine());

        dp = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<i+1; j++) {
                dp[i][j] = stoi(st.nextToken());
            }
        }
        
        // 다이나믹 프로그래밍으로 2번째 줄 부터 내려가면서 확인
        for(int i=1; i<N; i++) {
            for(int j=0; j<i+1; j++) {
                int upLeft, up;
                // 왼쪽 위에서 내려오는 경우
                if(j==0) upLeft = 0;
                else upLeft = dp[i-1][j-1];
                // 바로 위에서 내려오는 경우
                if(j==i) up = 0; // 위에가 없음
                else up = dp[i-1][j];
                // 최대 합 저장
                dp[i][j] = dp[i][j] + Math.max(upLeft, up);
            }
        }

        int ans = 0;
        // 맨 마지막 줄의 값 중 최대값이 답
        for(int j=0; j<N; j++) {
            ans = Math.max(ans, dp[N-1][j]);
        }
        System.out.println(ans);
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
