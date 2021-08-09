import java.io.*;
import java.util.*;
// 210809

public class Main_BJ_1439_뒤집기 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        int cnt0 = 0, cnt1 = 0;
        if(input[0]=='0') ++cnt1;
        else ++cnt0;

        for(int i=0; i<input.length-1; i++) {
            if(input[i]!=input[i+1]) {
                if(input[i+1]=='0') ++cnt1;
                else ++cnt0;
            }
        }

        System.out.println(Math.min(cnt0, cnt1));

        br.close();
    }
}