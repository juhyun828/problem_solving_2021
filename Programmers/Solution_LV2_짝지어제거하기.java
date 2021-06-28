import java.util.*;
// 210628

class Solution_LV2_짝지어제거하기
{
    public int solution(String s)
    {   int answer = 0;
        Stack<Character> stack = new Stack<Character>();
        for(char c: s.toCharArray()) {
            if(!stack.isEmpty() && stack.peek() == c) stack.pop();
            else stack.push(c);
        }

        if(stack.isEmpty()) answer = 1;
        return answer;
    }
}