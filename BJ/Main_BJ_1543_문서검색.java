import java.io.*;
import java.util.*;
// 210727

/*
< 반례 >
aabb
ab -> 답은 1이 되어야 한다.
str.replaceFirst(target, "")으로 처리할 경우, 제거되는 지점의 앞뒤가 붙어서 새로운 문자열을 만든다.
 */

public class Main_BJ_1543_문서검색 {
    static int max;
    static String str;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        max = 0;
        str = br.readLine();
        String target = br.readLine();

        while (true) {
            if (!find(target)) break;
        }

        System.out.println(max);

        br.close();
    }

    static boolean find(String target) {
        if(str.contains(target)) {
            str = str.replaceFirst(target, "!");
            ++max;
            return true;
        } else {
            return false;
        }
    }
}
