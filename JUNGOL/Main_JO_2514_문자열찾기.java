import java.io.*;
import java.util.*;
// 210605

public class Main_JO_2514_문자열찾기 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int kCnt = 0, iCnt = 0;

        for(int index=0; index<str.length()-2; ) {
            if(str.charAt(index)=='K' && str.charAt(index+1)=='O' && str.charAt(index+2)=='I') {
                ++kCnt;
                index +=2;
            } else if(str.charAt(index)=='I' && str.charAt(index+1)=='O' && str.charAt(index+2)=='I') {
                ++iCnt;
                index+=2;

            } else {
                index+=1;
            }
        }
        System.out.println(kCnt + "\n" + iCnt);
        br.close();
    }
}
