import java.io.*;
import java.util.*;
// 210825

public class Main_BJ_15486_퇴사2 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = stoi(br.readLine());
        int[] T = new int[N+2];
        int[] P = new int[N+2];
        int[] dp = new int[N+2];
        int max = 0;

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            T[i] = stoi(st.nextToken());
            P[i] = stoi(st.nextToken());
        }

        for(int i=1; i<=N+1; i++) {
            max = Math.max(max, dp[i]); // 이전에 계산된, i일까지 일했을 때의 최대값
            
            int day = i + T[i]; // 상담 시작 날부터 걸리는 날짜가 지나 금액을 받을 수 있는 날
            if(day <= N+1) {
                dp[day] = Math.max(max+P[i], dp[day]);
            }

        }

        System.out.println(max);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}