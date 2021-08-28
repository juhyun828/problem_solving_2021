import java.io.*;
import java.util.*;
// 210828

public class Main_BJ_2231_분해합 {
    static int min;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());

        for(int i=1; i<=N; i++) {
            int res = cal(i);
            if(res==N) {
                System.out.println(i);
                System.exit(0);
            }
        }

        System.out.println(0);

        br.close();
    }
    
    static int cal(int num) {
        String str = Integer.toString(num);
        int sum = 0;

        for(char c: str.toCharArray()) {
            sum += c - '0';
        }

        sum += num;
        return sum;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}