import java.util.*;
// 210511

class Solution_LV2_메뉴리뉴얼_fail {
    static Map<String, Integer> map;
    static boolean[] v;
    static char[] alp;

    public String[] solution(String[] orders, int[] course) {
    	ArrayList<String> res = new ArrayList<String>();
        Arrays.sort(orders);
        
        // 모든 알파벳들 구하기
        HashSet<Character> set = new HashSet<Character>();
        for(String order: orders) {
        	for(char c: order.toCharArray()) {
        		set.add(c);
        	}
        }
        alp = new char[set.size()];
        Iterator<Character> it = set.iterator();
        int idx=0;
        while(it.hasNext()) {
        	alp[idx++] = it.next();
        }
        
        for(int i=0; i<course.length; i++) {
        	map = new HashMap<String, Integer>();
        	v = new boolean[alp.length];
        	combination(course[i], 0, 0);
        	// 만들어진 코스 
        	int max = count(orders);
        	if(max<2) continue;
        	for(String key: map.keySet()) {
        		if(map.get(key)==max) {
        			res.add(key);
        		}
        	}
        	
        }
       
        Collections.sort(res);
        String[] ans = new String[res.size()];
        for(int i=0; i<res.size(); i++) {
        	ans[i] = res.get(i);
        }
        
        return ans;
    }
    
    // 1. 개수별로 고를 수 있는 조합
    static void combination(int N, int L, int cnt) {
    	if(cnt>=N) {
    		StringBuilder sb = new StringBuilder();
    		for(int i=0; i<v.length; i++) {
    			if(v[i]) {
    				sb.append(alp[i]);
    			}
    		}
    		
    		map.put(sb.toString(), 0);
    		
    		return;
    	}
    	
    	if(L>=alp.length) {
    		return;
    	}
    	
    	v[L] = true;
    	combination(N, L+1, cnt+1);

    	v[L] = false;
    	combination(N, L+1, cnt);
    }
  
    // 2. 만들어진 코스별로 세기
    static int count(String[] orders) {
    	for(String key: map.keySet()) {
    		for(String order: orders) {
    			boolean contains = true;
    			
    			for(char c: key.toCharArray()) {
    				if(!order.contains(Character.toString(c))) {
    					contains = false;
    					break;
    				}
    			}

    			if(contains) map.put(key, map.get(key)+1);
    		}
    	}
    	// max 값 반환
    	int max = 0;
    	for(String key: map.keySet()) {
    		if(max<map.get(key)) max = map.get(key);
    	}
    	
    	return max;
    	
    }
}