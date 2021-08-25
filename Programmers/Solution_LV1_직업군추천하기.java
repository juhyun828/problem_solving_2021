import java.util.*;
// 210825

class Solution_LV1_직업군추천하기 {
    public String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";
        Map<String, Integer> resMap = new LinkedHashMap<String, Integer>(); // 입력 순서가 보장되는 해시
        Map<String, Integer> preMap = new HashMap<String, Integer>();
        int max = 0; // 총합이 가장 높은 직업군 찾기
        
        Arrays.sort(table);
        for(int i=0; i<languages.length; i++) {
            preMap.put(languages[i], preference[i]);
        }
        
        for(String t: table) {
            System.out.println(t);
            String[] info = t.split(" ");
            String job = info[0];
            int total = 0;
            
            for(int i=1; i<=5; i++) {
                String lan = info[i];
                if(preMap.containsKey(lan)) {
                    total += (6-i) * preMap.get(lan);
                } else {
                    total += 6-i;
                }
            }
            resMap.put(job, total);
            max = Math.max(max, total);
        }
        
        for(String key: resMap.keySet()) {
            if(resMap.get(key) == max) {
                answer = key;
                return answer;
            }
        }
        
        return answer;
    }
}