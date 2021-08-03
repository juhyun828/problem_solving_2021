import java.io.*;
import java.util.*;
// 210611

public class Main_BJ_1003_피보나치함수 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int max = 41;
        int[] zero = new int[max];
        int[] one = new int[max];

        zero[0] = 1;
        zero[1] = 0;
        one[0] = 0;
        one[1] = 1;

        for(int i=2; i<max; i++) {
            if(zero[i]==0) zero[i] = zero[i-2] + zero[i-1];
            if(one[i]==0) one[i] = one[i-2] + one[i-1];
        }

        int T = stoi(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            int N = stoi(br.readLine());
            sb.append(zero[N] + " " + one[N] + "\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
