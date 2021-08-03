import java.util.*;
// 210707

class Solution_LV2_순위검색 {
    static Map<String, List<Integer>> map;
    
	public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
        // 조건 : [점수] 해시 생성
        map = new HashMap<String, List<Integer>>();
        
        // 1. info마다 조건 생성
        for(String i: info) {
           dfs(0, "", i.split(" "));
        }
        
        // 2. 조건마다 생성된 [점수] 리스트 정렬
        for(String key: map.keySet()) {
            Collections.sort(map.get(key));
        }
        
        /// 3. query별 인원 구하기
        for(int i=0; i<query.length; i++) {
            String[] queryArr = query[i].split(" ");
            String key = queryArr[0] + queryArr[2] + queryArr[4] + queryArr[6];
            int score = Integer.parseInt(queryArr[7]);
            answer[i] = binarySearch(key, score);
        }
        
		return answer;
	}
    
    static int binarySearch(String key, int num) {
        if(!map.containsKey(key)) return 0;
        
        List<Integer> list = map.get(key);
        int start=0, end=list.size()-1;
        
        while(start<=end) {
            int mid = (start+end)/2;        
            if(list.get(mid)<num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return list.size()-start;
    }
    
    static void dfs(int L, String condition, String[] infoArr) {
        if(L==4) {
            if(!map.containsKey(condition)) {
                map.put(condition, new ArrayList<Integer>());
            } 
            
            int score = Integer.parseInt(infoArr[4]);
            map.get(condition).add(score);
            return;
        }
        
        dfs(L+1, condition+infoArr[L], infoArr);
        dfs(L+1, condition+"-", infoArr);
    }
}