import java.io.*;
import java.util.*;
// 210725

public class Main_BJ_1874_스택수열 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = stoi(br.readLine());

        Stack<Integer> st = new Stack<Integer>();

        int cnt = 1;
        for(int i=1; i<=n; i++) { // 입력으로 들어오는 n개의 데이터
            int input = stoi(br.readLine());

            while (cnt <= input) {
                st.push(cnt);
                cnt++;
                sb.append("+\n");
            }

            if(st.peek()==input) {
                st.pop();
                sb.append("-\n");
            } else {
                System.out.println("NO");
                return;
            }
        }

        System.out.println(sb.toString());
        br.close();
    }
    
    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
