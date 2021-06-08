import java.io.*;
import java.util.*;
// 210609

// 1. char to String 변환 : Character.toString(ch);
// 2. String 대문자 변환 : str.toUpperCase()
// 3. char 대문자 변환 : (char) ('b' + ('A'-'a'))
// 4. 소문자가 대문자보다 아스키값이 크다. 순서가 더 뒤기 때문이다.

public class Main_JO_1880_암호풀기 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] key = br.readLine().toCharArray();
        String encrypted = br.readLine();
        String ans = "";
        int upper = 'A' - 'a';
        int idx = 0;

        for(char c: encrypted.toCharArray()) {
            if(c==' ') ans += ' ';
            else if(c >= 'A' && c<='Z') { // 대문자인 경우
                idx = c - 'A';
                ans += (char) (key[idx] + upper);

            } else { // 소문자인 경우
                idx = c - 'a';
                ans += key[idx];
            }
        }

        System.out.println(ans);
        br.close();
    }
}
