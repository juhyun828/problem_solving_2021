import java.io.*;
import java.util.*;
// 210823

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

    static void dfs(int L, String str) {
        if(L == N) {
            if(cal(str)== 0) {
                sb.append(str + "\n");
            }
            return;
        }

        dfs(L+1, str + " " + (L+1)); // 아스키 순으로
        dfs(L+1, str + "+" + (L+1));
        dfs(L+1, str + "-" + (L+1));
    }

    static int cal(String str) {
        // trim()은 맨 끝 공백만 제거함
        str = str.replace(" ", "");
        int num = 0, total = 0;
        char oper = '?';

        for(char c: str.toCharArray()) {
            if(c=='+' || c=='-') {
                // 이전 연산자 처리
                if(oper == '?') total = num;
                else if(oper == '+') total += num;
                else if(oper == '-') total -= num;
                num = 0; // 누적한 숫자 비우기
                oper = c; // 이번 연산자는 다음에 처리할 연산자가 된다.
            } else { // 숫자 누적
                num = num * 10 + (int)(c - '0');
            }
        }

        // 남은 연산자, 숫자 처리
        if(oper == '?') {
            total = num;
        } else if(oper == '+') {
            total += num;
        } else if(oper == '-') {
            total -= num;
        }

        return total;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}