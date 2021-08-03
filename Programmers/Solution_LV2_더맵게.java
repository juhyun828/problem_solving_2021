import java.util.*;
// 210629

class Solution_LV2_더맵게 {
    public int solution(int[] scoville, int K) {
        int cnt=0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();   
        for(int s: scoville) {
            pq.offer(s);
        }
        
        while(!pq.isEmpty()) {
            int s1 = pq.poll();
            if(s1 >= K) break;
            if(pq.isEmpty()) {
                cnt = -1;
                break;
            }
            int s2 = pq.poll();
            pq.offer(s1 + s2*2);
            ++cnt;
        }
        
        return cnt;
    }
}