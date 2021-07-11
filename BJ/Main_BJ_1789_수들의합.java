import java.io.*;
import java.util.*;
// 210711

public class Main_BJ_1789_수들의합 {
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long S = Long.parseLong(br.readLine());
        long sum = 0, num = 0;
        while(sum <= S) {
            sum += ++num;
        }

        System.out.println(sum>S ? num-1 : num);
        br.close();
    }
}
