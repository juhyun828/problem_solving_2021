import java.util.*;
// 210409

class Solution_LV3_입국심사 {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long min = Long.MAX_VALUE;
        long start = times[0];
        long end = (long)times[times.length-1] * (long)n;	
        
        while(start<=end) {
            long cnt = 0;
            long mid = (start+end)/2;
            // mid초동안 각각의 심사위원들은 mid/time명 심사 가능
            for(int time: times) {
                cnt += mid/time;
            }
            
            // n명 이상 검사 가능함
            if(cnt>=n) {
                min = Math.min(min, mid);
                // 더 작은 범위에서 검사해야 함
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
     
        return min;
    }
}