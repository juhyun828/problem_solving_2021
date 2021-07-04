import java.util.*;
// 210704

class Solution_LV2_괄호회전하기 {
    public int solution(String s) {  
        int res = rotate(s.toCharArray());
        return res;
    }
    
    static int rotate(char[] arr) {
        int cnt = 0;
        
        for(int x=0; x<arr.length; x++) {
            char[] rotated = new char[arr.length];
            for(int i=0; i<arr.length; i++) {
                rotated[i] = arr[(i+x)%arr.length];
            }
            if(isRight(rotated)) ++cnt;
        }
        return cnt;
    }
    
    static boolean isRight(char[] newArr) {
        Stack<Character> stack = new Stack<Character>();
        
        for(char c: newArr) {
            if(stack.isEmpty()) {
                stack.push(c);
            } 
            
            else {
                // 열린 괄호
                if(c == '(' || c == '[' || c == '{') { 
                    stack.push(c);
                } 
                // 닫는 괄호
                else if(c == ')' && stack.peek() == '(') {
                    stack.pop();
                    
                }else if(c == ']' && stack.peek() == '[') {
                    stack.pop();
                    
                } else if(c == '}' && stack.peek() == '{') {
                    stack.pop();
                    
                } 
                // 닫는 괄호지만 짝이 맞지 않음
                else { 
                    return false;
                }   
            }
        } // 
        if(stack.isEmpty()) return true;
        else return false;
    }
}