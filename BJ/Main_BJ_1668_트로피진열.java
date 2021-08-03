import java.io.*;
import java.util.*;
// 210728

public class Main_BJ_1668_트로피진열 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());
        int[] reversed = new int[N];
        int max = 0;
        int left = 0;

        for(int i=0; i<N; i++) {
            int num = stoi(br.readLine());
            reversed[N-1-i] = num;
            if(max < num) {
                ++left;
                max = num;
            }
        }
        System.out.println(left);

        max = 0;
        int right = 0;
        for(int i=0; i<N; i++) {
            if(max <  reversed[i]) {
                ++right;
                max = reversed[i];
            }
        }
        System.out.println(right);

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
