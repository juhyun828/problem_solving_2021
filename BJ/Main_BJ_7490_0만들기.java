import java.io.*;
import java.util.*;
// 210727

public class Main_BJ_7490_0만들기 {
    static int N;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sb = new StringBuilder();
        int T = stoi(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            if(tc>1) sb.append("\n");
            N = stoi(br.readLine());
            dfs(1, "1");
        }

        System.out.println(sb.toString());

    }

    static void dfs(int num, String str) {
        if(num==N) {
           if(cal(str)==0) {
               sb.append(str+"\n");
           }
            return;
        }

        dfs(num+1, str + " " + (num+1));
        dfs(num+1, str + "+" + (num+1));
        dfs(num+1, str + "-" + (num+1));
    }

    static int cal(String str) {
        str = str.replace(" ", "");

        char oper = '?';
        int num = 0, res = 0;

        for(char c: str.toCharArray()) {
            if(c=='+' || c=='-') { // 연산자
                if(oper=='?') {
                    res = num;
                } else if(oper == '+') {
                    res += num;
                } else if(oper == '-') {
                    res -= num;
                }

                num = 0;
                oper = c;
                
            } else { // 숫자
                num = num*10 + (int)(c-'0');
            }
        }

        // 남은 연산자, 숫자 처리
        if(oper=='?') {
            res = num;
        } else if(oper == '+') {
            res += num;
        } else if(oper == '-') {
            res -= num;
        }

        return res;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
