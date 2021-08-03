import java.util.*;
// 210706
class Solution_LV1_신규아이디추천 {
    public String solution(String new_id) {
        String answer = "";
        new_id = new_id.toLowerCase();
        
        char prev = '?';
        for(char c: new_id.toCharArray()) {
            if(c=='.') {
                if(prev=='.') continue;
                else {
                    answer += c;
                    prev = c;
                }
            } else if(c=='-' || c=='_' || (c>='0'&&c<='9') || (c>='a'&&c<='z')) {
                answer += c;
                prev = c;
            }
        }
        
        // 빈 문자열
        if(answer.equals("")) answer = "aaa";

        // 맨 앞의 . 제거
        if(answer.charAt(0)=='.') {
            if(answer.length()==1) answer = "aaa"; // 사라져서 빈 칸이 됨 
            else {
                answer = answer.substring(1, answer.length());
            }
        }
        
        // 맨 뒤의 . 제거
        if(answer.charAt(answer.length()-1)=='.') {
            answer = answer.substring(0, answer.length()-1);
        }
        
        if(answer.length()>=16) {
            answer = answer.substring(0, 15);
             // 맨 뒤의 . 제거
            if(answer.charAt(answer.length()-1)=='.') { 
                answer = answer.substring(0, answer.length()-1);
            }
        }
        
        char last = answer.charAt(answer.length()-1);
        while(answer.length()<=2) {
            answer += last;
        }
        return answer;
    }
}