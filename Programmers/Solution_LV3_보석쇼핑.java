import java.util.*;
// 210506

class Solution_LV3_보석쇼핑 {
    public int[] solution(String[] gems) {
        // 보석 종류 개수
        HashSet<String> set = new HashSet<>();
        for(String g: gems) {
        	set.add(g);
        }
        
        HashMap<String, Integer> map = new HashMap<>();// 보석 별 개수
        Queue<String> q = new ArrayDeque<String>(); 
        int start=0, startTmp=0, length=Integer.MAX_VALUE;
        
        for(String g: gems) {
        	if(map.containsKey(g)) {
        		map.put(g, map.get(g)+1);
        		q.offer(g);
        	} else {
        		map.put(g, 1);
        		q.offer(g);
        	}
        	
        	// 같은 보석이 나오면 이전에 나온 보석을 없앤다.
        	while(true) {
        		String front = q.peek();
        		if(map.get(front)>1) {
        			map.put(front, map.get(front)-1);
        			q.poll();
        			++startTmp;
        			
        		} else break;
        	}
        	
        	// 모든 종류의 보석이 map에 들어있고, 길이가 더 작은 경우 경우 
        	if(set.size()==map.size() && q.size()<length) {
        		start = startTmp;
        		length = q.size();
        	}
        	
        }
        
        return new int[] {start+1, start+length};
    }
	
}