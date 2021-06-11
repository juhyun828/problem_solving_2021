import java.io.*;
import java.util.*;
// 210610

public class Main_JO_1027_좋은수열 {
    static int N;
    static String min = "";
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs(0, "");
		System.out.println(min);
        br.close();
    }

    static void dfs(int depth, String str) {
        if(!min.equals("")) return;
        if(str.length()>=2 && !isGood(str)) return;

        if(depth==N) {
            min = str;
            return;
        }

        for(int i=1; i<=3; i++) {
            dfs(depth+1, str+i);
            //dfs(depth+1, str+Integer.toString(i));
        }
    }

    static boolean isGood(String str) {
        int l = str.length();

        if(l==2) {
            String sub1 = str.substring(0, 1);
            String sub2 = str.substring(1, 2);
            if(sub1.equals(sub2)) return false;
        }

        for(int i=1; i<=l/2; i++) {
            String sub1 = str.substring(l-i);
            String sub2 = str.substring(l-2*i, l-i);
            if(sub1.equals(sub2)) {
                return false;
            }
        }

        return true;
    }
}
