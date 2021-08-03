import java.io.*;
import java.util.*;
// 210616

public class Main_BJ_2565_전깃줄 {
    static class Line implements Comparable<Line> {
        int a, b;

        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line o) {
            // A 전깃줄 기준 오름차순 정렬
            return Integer.compare(this.a, o.a);
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = stoi(br.readLine());

        Line[] arr = new Line[N+1];
        arr[0] = new Line(0, 0); // 첫번째 요소 채워주지 않은 채 정렬하면 NullPointerException
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i] = new Line(stoi(st.nextToken()), stoi(st.nextToken()));
        }

        Arrays.sort(arr);
        // A 기준으로 이미 오름차순 정렬되어 있다면, B가 증가하는 순열일 경우 겹치지 않는다.
        // 따라서 B기준으로 LIS를 구하면 겹치지 않고 최대한 많이 설치할 수 있는 경우의 수다.

        int[] dp = new int[N+1];
        dp[0] = 0;

        int max = 0; // 최대한 많이 설치
        for(int i=1; i<=N; i++) {
            dp[i] = 1;
            for(int j=1; j<=i; j++) {
                if(arr[j].b < arr[i].b) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(N-max);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
