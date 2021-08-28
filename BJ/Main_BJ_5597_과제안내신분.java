import java.io.*;
import java.util.*;
// 210828

public class Main_BJ_5597_과제안내신분 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] v = new boolean[31];
        for(int i=0; i<28; i++) {
            int num = stoi(br.readLine());
            v[num] = true;
        }

        for (int i=1; i<=30; i++) {
            if(!v[i]) System.out.println(i);
        }

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}