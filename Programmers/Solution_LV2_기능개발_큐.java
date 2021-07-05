import java.util.*;
// 210706

class Solution_LV2_기능개발_큐 {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int len = progresses.length;
        Queue<Integer> q = new ArrayDeque<Integer>();
        
        for(int i=0; i<len; i++) {
            int days = (100-progresses[i])/speeds[i];
            if((100-progresses[i])%speeds[i]!=0) days+=1; 
            
            if(!q.isEmpty() && q.peek() < days) { // 앞의 기능들 배포 가능
                res.add(q.size());
                q.clear();
            }
            
            q.offer(days);
        } 
        res.add(q.size());
        
        int[] answer = new int[res.size()];
        for(int i=0; i<res.size(); i++) {
            answer[i] = res.get(i);
        }
        return answer;
    }
}