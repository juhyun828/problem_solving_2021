import java.io.*;
import java.util.*;
// 211012

public class Main_BJ_1662_압축_fail {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        Stack<Character> stack = new Stack<Character>();
        String str = "";
        int cnt = 0;

        for(char ch: input.toCharArray()) {
            if(ch!=')') {
                stack.push(ch);
            } else {
                while (true) {
                    if(stack.peek()=='(') {
                        stack.pop();
                        break;
                    } else {
                        str += stack.pop();
                    }
                }
                cnt = stack.pop()-'0';
                for(int i=0; i<cnt; i++) {
                    for(char j: str.toCharArray()) {
                        stack.add(j);
                    }
                }
                cnt = 0;
                str = "";
            }
        }

        System.out.println(stack.size());
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}