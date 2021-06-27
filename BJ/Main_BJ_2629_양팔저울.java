import java.io.*;
import java.util.*;
// 210625

public class Main_BJ_2629_양팔저울 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine()); // 구슬 개수
        int[] balls = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++) {
            balls[i] = stoi(st.nextToken());
        }

        boolean[][] dp = new boolean[N+1][40001]; // 구슬 최대 무게 40,000
        dp[1][balls[1]] = true;

        for(int i=2; i<=N; i++) {
            int w = balls[i]; // i번째 구슬까지 따졌을 때
            dp[i][w] = true;
            for(int k=1; k<=40000; k++) {
                if(dp[i-1][k]) { // i-1번 구슬까지 따졌을 때 알 수 있는 무게라면
                    dp[i][k] = true;
                    int diff = Math.abs(w-k);
                    dp[i][diff] = true;
                    int plus = w+k;
                    if(plus>40000) continue;
                    dp[i][plus] = true;
                }
            }
        }

        int M = stoi(br.readLine()); // 무게 개수
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++) {
            int target = stoi(st.nextToken());
            if(dp[N][target]) System.out.print("Y");
            else System.out.print("N");
            if(i!=(M-1)) System.out.print(" ");
        }

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}