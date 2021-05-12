import java.util.*;
// 210512

class Solution_LV2_메뉴리뉴얼 {
	static Map<String, Integer> map;
	static int maxCnt;

    public String[] solution(String[] orders, int[] course) {
    	ArrayList<String> list = new ArrayList<String>();
    	
    	for(int i=0; i<course.length; i++) {
    		map = new HashMap<String, Integer>();
    		maxCnt = 0;
    		
    		for(String order: orders) {
    			combination(order, "", course[i], 0, 0);
    		}
    		
    		System.out.println(maxCnt);
    		if(maxCnt<2) continue;
    		for(String key: map.keySet()) {
    			if(map.get(key)==maxCnt) {
    				list.add(key);
    			}
    		}
    		
    	}
    	
    	String[] ans = new String[list.size()];

    	Collections.sort(list);
    	for(int i=0; i<list.size(); i++) {
    		ans[i] = list.get(i);
    	}
        
        return ans;
    }

    // NCR : N = order.length, R: course[i]
	static void combination(String order, String selected, int R, int start, int L) {
		if(L==R) {
			char[] res = selected.toCharArray();
			Arrays.sort(res);
			// 다시 문자열로
			String resStr = "";
			for(char c: res) {
				resStr += c;
			}
			// map에 넣기
			if(map.containsKey(resStr)) {
				map.put(resStr, map.get(resStr)+1);
			} else {
				map.put(resStr, 1);
			}
			maxCnt = Math.max(maxCnt, map.get(resStr)); 
			return;
		}
		
		for(int i=start; i<order.length(); i++) {
			combination(order, selected+order.charAt(i), R, i+1, L+1);
		}
		
	}
  
}