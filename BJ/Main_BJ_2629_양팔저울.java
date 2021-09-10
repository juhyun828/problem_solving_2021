import java.io.*;
import java.util.*;
// 210910

public class Main_BJ_2629_양팔저울 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int max = 40000;
        int N = stoi(br.readLine()); // 구슬 개수
        int[] balls = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++) {
            balls[i] = stoi(st.nextToken());
        }
        int M = stoi(br.readLine()); // 무게 개수
        st = new StringTokenizer(br.readLine());
        int[] target = new int[M];
        for(int i=0; i<M; i++) {
            target[i] = stoi(st.nextToken());
        }

        boolean[][] dp = new boolean[N+1][max+1]; // 구슬 최대 무게 40,000
        // dp[i][j] -> i번째 구슬까지 살폈을 때 무게 j를 잴 수 있다.
        dp[1][balls[1]] = true;

        for(int i=2; i<=N; i++) { // i번째 구슬 까지 따졌을 때 잴 수 있는 무게들 체크
            int w = balls[i]; // i번째 구슬 무게
            dp[i][w] = true;
            for(int k=1; k<=max; k++) {
                if(dp[i-1][k]) { // i-1번째 구슬 까지 따졌을 때 잴 수 있는 무게라면
                    dp[i][k] = true; // i번째 구슬을 쓰지 않으면 잴 수 있는 무게
                    int diff = Math.abs(w-k); // 이번 구슬의 무게와 이전까지 가능한 무게의 차이
                    dp[i][diff] = true;
                    int plus = w+k; // 이번 구슬의 무게와 이전까지 가능한 무게의 합
                    if(plus<=max) dp[i][plus] = true;
                }
            }
        }

        for(int i=0; i<M; i++) {
            if(dp[N][target[i]]) System.out.print("Y"); // 마지막 구슬까지 따졌을 때 잴 수 있는 무게
            else System.out.print("N");
            if(i<M-1) System.out.print(" ");
        }

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}