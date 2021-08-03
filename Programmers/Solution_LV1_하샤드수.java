import java.util.*;
// 210716

class Solution_LV1_하샤드수 {
    public boolean solution(int x) {
        boolean answer = false;
        
        String str = Integer.toString(x);
        int sum = 0;
        for(String s: str.split("")) {
            sum += Integer.parseInt(s);
        }
        
        if(x%sum==0) answer = true;
        
        return answer;
    }
}