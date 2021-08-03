import java.io.*;
import java.util.*;
// 210709

public class Main_BJ_16953_AB {
    static int A, B;
    static long min;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = stoi(st.nextToken());
        B = stoi(st.nextToken());
        min = Long.MAX_VALUE;
        dfs(1, A);
        System.out.println(min==Long.MAX_VALUE ? -1 : min);

        br.close();
    }

    static void dfs(int cnt, long res) {
        if(res > B) return;
        if(res==B) {
            min = Math.min(min, cnt);
        }

        dfs(cnt+1, res*2);
        long num = Long.parseLong(Long.toString(res) + "1");
        dfs(cnt+1, num);

    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}