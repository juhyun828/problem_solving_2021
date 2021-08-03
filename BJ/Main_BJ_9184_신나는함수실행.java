import java.io.*;
import java.util.*;
// 210611

public class Main_BJ_9184_신나는함수실행 {
    static int[][][] dp = new int[51][51][51];

    static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0)
            return 1;

        if (a > 20 || b > 20 || c > 20)
            return dp[a][b][c] = w(20, 20, 20);

        if(dp[a][b][c]!=0) return dp[a][b][c];

        if (a < b && b < c)
            return dp[a][b][c] = w(a, b, c-1)
                    + w(a, b-1, c-1)
                    - w(a, b-1, c);

        return dp[a][b][c] = w(a-1, b, c)
                + w(a-1, b-1, c)
                + w(a-1, b, c-1)
                - w(a-1, b-1, c-1);
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine(),  " ");
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            int c = stoi(st.nextToken());

            if(a==-1 && b==-1 && c==-1) break;
            else {
                int res = w(a, b, c);
                sb.append("w(" + a + ", " + b + ", " + c +") = " + res +"\n");
            }
        }

        System.out.println(sb.toString());
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
