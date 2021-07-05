import java.util.*;
// 210705

class Solution_LV2_프린터 {
    static class Document {
        int priority, idx;
        public Document(int priority, int idx) {
            this.priority = priority;
            this.idx = idx;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int order = 0;
        Queue<Document> q = new ArrayDeque<Document>();
        for(int i=0; i<priorities.length; i++) {
            q.offer(new Document(priorities[i], i));    
        }
        Iterator<Document> it;
        
        while(!q.isEmpty()) {
            Document cur = q.poll();
            // 남은 문서들 중 더 높은 우선순위가 있는지 살펴본다.
            it = q.iterator();
            boolean later = false;
            while(it.hasNext()) {
                Document next = it.next();
                if(next.priority > cur.priority) {
                    q.offer(cur);
                    later = true;
                    break;
                }
            }
            // 더이상 높은 우선 순위가 없다면
            if(!later) {
                ++order;
                if(cur.idx == location) {
                    answer = order;
                    break;
                }
            }
        }
        return answer;
    }
}