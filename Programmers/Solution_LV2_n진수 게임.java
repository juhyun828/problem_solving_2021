import java.util.*;
//210905

class Solution_LV2_n진수 게임 {
    static String str;
    static char[] nums = new char[] {'0', '1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G'};
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        str = "0";
        int cur = 1;

        while(str.length()<=m*t+1) {
            String converted = convert(n, cur++);
            str += converted;
        }
        
        // 튜브는 p-1의 배수 번째 숫자만 t번 말한다.
        for(int i=0; i<t; i++) {
            answer += str.charAt((p-1)+m*i);
        }

        return answer;
    }

    static String convert(int n, int cur) {
        Stack<Integer> stack = new Stack<Integer>();
        while(true) {
            stack.add(cur%n);
            cur/=n;
            if(cur<n) {
                if(cur!=0)
                    stack.add(cur);
                break;
            }
        }

        // n진수 숫자로 변환
        String res = "";
        while(!stack.isEmpty()){
            int tmp = stack.pop();
            res += nums[tmp];
        }
        return res;
    }

}