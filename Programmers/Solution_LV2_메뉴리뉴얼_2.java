import java.util.*;
// 210706

class Solution_LV2_메뉴리뉴얼_2 {
    static Map<String, Integer>[] courseMap;
    static int maxCourseCnt;
    
    public String[] solution(String[] orders, int[] course) {
        String[] ans = new String[] {};
        ArrayList<String> res = new ArrayList<String>();
        maxCourseCnt = 0;
        for(int i: course) {
            maxCourseCnt = Math.max(maxCourseCnt, i);
        }
        
        courseMap = new HashMap[maxCourseCnt+1];
        for(int i=2; i<=maxCourseCnt; i++) {
            // courseMap[i]는 i가지 단품 요리를 담은 코스 요리: 횟수 
            courseMap[i] = new HashMap<String, Integer>();
        }

        for(String order: orders) {
            // orders의 구성 요소를 오름차순으로 정렬한다.
            char[] orderCharArr = order.toCharArray();
            Arrays.sort(orderCharArr);
            dfs(0, 0, "", String.valueOf(orderCharArr));
        }

        for(int i: course) {
            if(courseMap[i].size()==0) continue;
            int max = Collections.max(courseMap[i].values());
            // 최대 조합 수를 가진 코스를 찾는다.
            if(max==1) continue;
            for(String key: courseMap[i].keySet()) {
                if(courseMap[i].get(key) == max) {
                    res.add(key);
                }
            }
        }
        
        // 정렬 후 정답 리턴
        Collections.sort(res);
        ans = new String[res.size()];
        for(int i=0; i<res.size(); i++) {
            ans[i] = res.get(i);
        }
        
        return ans;
    }

    static void dfs(int L, int cnt, String course, String order) {
        if(cnt>maxCourseCnt) return;
        if(L==order.length()) {
            if(2<=cnt && cnt<=maxCourseCnt) {
                if(courseMap[cnt].containsKey(course)) {
                    courseMap[cnt].put(course, courseMap[cnt].get(course)+1);
                } else {
                    courseMap[cnt].put(course, 1);
                }
            } 
            return;
        }
        
        dfs(L+1, cnt+1, course+order.charAt(L), order);
        dfs(L+1, cnt, course, order);
    }
}