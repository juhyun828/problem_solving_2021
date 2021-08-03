import java.io.*;
import java.util.*;
// 210724

public class Main_BJ_2920_음계 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        String asc = "12345678", des = "87654321";
        String input = "";
        String answer = "mixed";

        for(int i=0; i<8; i++) {
            input += st.nextToken();
        }

        if (asc.equals(input)) {
            answer = "ascending";

        } else if(des.equals(input)) {
            answer = "descending";
        }

        System.out.println(answer);

        br.close();
    }
}