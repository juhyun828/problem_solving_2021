import java.io.*;
import java.util.*;
// 210727

public class Main_BJ_1568_새 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());
        int sec = 0;
        int K = 1;

        while (total > 0) {
            if(K > total) {
                K = 1;
            }

            total -= K;
            K++;
            sec++;
        }

        System.out.println(sec);
        br.close();
    }
}
