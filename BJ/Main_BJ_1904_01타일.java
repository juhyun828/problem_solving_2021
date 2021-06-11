import java.io.*;
import java.util.*;
// 210611

// 모듈러 연산의 분배법칙 : (A+B)%C = (A%C + B%C)%C

public class Main_BJ_1904_01타일 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());

        int[] dp = new int[10000001];
		dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<=N; i++) {
            dp[i] = (dp[i-2] + dp[i-1])%15746;
        }

        System.out.println(dp[N]);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
