import java.io.*;
import java.util.*;
// 210823

public class Main_BJ_1343_폴리오미노 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        String str = "";

        for (char c: input.toCharArray()) {
            if(c == '.') {
                if(str.length()>0) {
                    int len = str.length();
                    if(len % 2 == 1) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                    while (len>=4) {
                        str = str.replaceFirst("XXXX", "AAAA");
                        len -= 4;
                    }

                    while (len>=2) {
                        str = str.replaceFirst("XX", "BB");
                        len -= 2;
                    }
                    sb.append(str);
                    str = "";
                }

                sb.append(c);
            } else {
                str += c;
            }
        }

        // 마지막 처리
        if(str.length()>0) {
            int len = str.length();
            if(len % 2 == 1) {
                System.out.println(-1);
                System.exit(0);
            }

            while (len>=4) {
                str = str.replace("XXXX", "AAAA");
                len -= 4;
            }

            while (len>=2) {
                str = str.replace("XX", "BB");
                len -= 2;
            }
            sb.append(str);
        }

        System.out.println(sb.toString());

        br.close();
    }

}