import java.util.*;

class Solution_LV2_전화번호목록 {
    public boolean solution(String[] phone_book) {
    	Map<String, Boolean> map = new HashMap<>();
    	
    	for(String pn: phone_book) {
    		map.put(pn, true);
    	}
    	
    	for(String pn: phone_book) {
    		StringBuilder sb = new StringBuilder();
    		for (char c: pn.toCharArray()) {
    			sb.append(c);
    			if(map.get(sb.toString())!=null && !sb.toString().equals(pn)) {
    				return false;
    			}
    		}
    	}

        return true;
    }
}