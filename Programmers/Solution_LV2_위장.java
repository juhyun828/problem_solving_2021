import java.util.*;
// 210429

class Solution_LV2_위장 {
    public int solution(String[][] clothes) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
        
        for(String[] clothe: clothes){
            String key = clothe[1];
            if(map.get(key)==null) map.put(key, 1);
            else map.put(key, map.get(key)+1);
        }
        
        int ans = 1;
        for(int value: map.values()) {
        	ans *= (value+1);
        }
        
        return ans-1;
    }
}