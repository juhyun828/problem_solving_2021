import java.io.*;
import java.util.*;
// 210808

public class Main_BJ_5585_거스름돈 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int changes = 1000 - Integer.parseInt(br.readLine());
        int[] money = new int[] {500, 100, 50, 10, 5, 1};
        int cnt = 0;

        for(int m: money) {
            cnt += changes / m;
            changes %= m;
        }

        System.out.println(cnt);

        br.close();
    }
}
