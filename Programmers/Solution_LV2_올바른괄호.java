import java.util.*;

// 210723

class Solution_LV2_올바른괄호 {
    boolean solution(String s) {
        boolean answer = true;
        
        Stack<Character> stack = new Stack<Character>();
        for(char c: s.toCharArray()) {
            if(c=='(') {
                stack.push(c);
                
            } else if(c==')') {
                if(stack.isEmpty() ) {
                    answer = false;
                    return answer;
                    
                } else {
                    if(stack.peek()=='(') {
                        stack.pop();
                        
                    } else {
                        answer = false;
                        return answer;
                    }        
                }
            }
        }
        
        if(!stack.isEmpty()) answer = false;

        return answer;
    }
}