import java.io.*;
import java.util.*;
// 210813

public class Main_BJ_1781_컵라면_fail {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = stoi(br.readLine());
        int total = 0;
        int[] check = new int[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = stoi(st.nextToken());
            int cnt = stoi(st.nextToken());
            if(check[deadline]==0) {
               check[deadline] = cnt;
               total += cnt;
            } else if(check[deadline] > 0 && check[deadline] < cnt) {
                total -= check[deadline];
                check[deadline] = cnt;
                total += cnt;
            }
        }

        System.out.println(total);
    }
    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}