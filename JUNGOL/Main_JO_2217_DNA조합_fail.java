import java.io.*;
import java.util.*;
// 210610

public class Main_JO_2217_DNA조합_fail {
    static int N, min;
    static String[] input;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N  = Integer.parseInt(br.readLine());
        input = new String[N];
        v = new boolean[N];
        min = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            input[i] = br.readLine();
        }

        dfs(0, "");

        System.out.println(min);

        br.close();
    }

    static void dfs(int idx, String str) {
        if(str.length()>min) return;

        if(idx>=N) {
            min = Math.min(min, str.length());
            System.out.println(str);
            return;
        }

        for(int i=0; i<N; i++) {
            if(v[i]) continue;
            v[i] = true;
            dfs(idx+1, unduplicate(str, input[i]));
            v[i] = false;
        }
    }

    static String unduplicate(String front, String back) {
        String duplicated = "", res = "";
        int len = Math.min(front.length(), back.length());
        int idx = 0;
        for(int i=1; i<=len; i++) {
            String frontSub = front.substring(front.length()-i);
            String backSub = back.substring(0, i);
//            System.out.println(frontSub + " " + backSub);
            if(frontSub.equals(backSub)) {
                duplicated = frontSub;
                idx = i;
            }
        }
        if(duplicated.equals("")) {
            // 겹치는 부분이 없음
            res = front + back;
        } else {
            // 겹치는 부분 제외
            res = front + back.substring(idx);
        }
        return res;
    }
}
