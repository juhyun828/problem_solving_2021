import java.io.*;
import java.util.*;
// 210608

public class Main_JO_2606_그릇 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int height = 10;
        String str = br.readLine();
        char state = str.charAt(0);

        for(int i=1; i<str.length(); i++) {
            char cur = str.charAt(i);
            if(state==cur) {
                height += 5;
            } else {
                height += 10;
                state = cur;
            }
        }

        System.out.println(height);
        br.close();
    }
}
