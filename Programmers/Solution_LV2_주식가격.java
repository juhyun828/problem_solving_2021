import java.util.*;

class Solution_LV2_주식가격 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        for(int i=0; i<prices.length-1; i++) {
            int j = i+1;
            while(j<prices.length-1) {
                if(prices[j] >= prices[i]) {
                    j++;
                } 
                else break;
            }
            
            answer[i] = j-i;
        }
        
        return answer;
    }
}