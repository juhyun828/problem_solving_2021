import java.util.*;
// 210829

class Solution_LV1_다트게임 {
    
    static class Chance {
        int score;
        char bonus, option;
        
        public Chance(int score, char bonus, char option){
            this.score = score;
            this.bonus = bonus;
            this.option = option;
        }
      
    }
    
    public int solution(String dart) {
        int answer = 0;
        Chance[] arr = new Chance[3];
        
        int i = 0;
        int idx = 0;
        while(idx<dart.length()) { 
            if(dart.charAt(idx)=='1' && dart.charAt(idx+1)=='0') { // 점수가 10점인 경우
           
                if(idx+3<dart.length() && (dart.charAt(idx+3) == '*' || dart.charAt(idx+3)=='#')){
                    // 옵션 포함
                    arr[i++] = new Chance(10, dart.charAt(idx+2), dart.charAt(idx+3));
                    idx+=4;
                    
                } else {
                    // 옵션 미포함
                    arr[i++] = new Chance(10, dart.charAt(idx+2), '?');
                    idx+=3;
                }
            } else { // 점수가 10보다 작을 경우
                if(idx+2<dart.length() && (dart.charAt(idx+2)=='*' || dart.charAt(idx+2)=='#' )) {
                    // 옵션 포함
                    arr[i++] = new Chance(dart.charAt(idx)-'0', dart.charAt(idx+1), dart.charAt(idx+2) );
                    idx+=3;
                } else { 
                    // 옵션 미포함
                    arr[i++] = new Chance(dart.charAt(idx)-'0', dart.charAt(idx+1), '?');
                    idx +=2;
                }
            }

        }
        
        // 보너스, 옵션에 따른 점수 계산
        for(i=0; i<3; i++) {
            Chance c = arr[i];
            if(arr[i].bonus=='D') {
                c.score = c.score * c.score;
                
            } else if(c.bonus=='T') {
                c.score = c.score * c.score * c.score;
            }
            
            if(c.option=='*') {
                // 스타상
                if(i>0) {
                    arr[i-1].score *= 2;
                } 
                c.score *=2;
                
            } else if(c.option=='#') {
                // 아차상
                c.score *= -1;
            }
            
        }
        
        // 최종 계산
        for(Chance c: arr) {
            answer += c.score;
        }
        
        return answer;
    }
}