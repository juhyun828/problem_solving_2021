import java.util.*;

class Solution_LV1_완주하지못한선수 {

    public String solution(String[] participant, String[] completion) {
    	Map<String, Integer> map = new HashMap<>();
    	
    	for(String p : participant) {
    		if(map.get(p)==null) map.put(p, 1);
    		else map.put(p, map.get(p)+1);
    	}
    	
    	for(String c: completion) {
    		map.put(c, map.get(c)-1);
    	}
    	
    	for(String key: map.keySet()) {
    		if(map.get(key)!=0) return key;
    	}
        
        return "";
    }
}