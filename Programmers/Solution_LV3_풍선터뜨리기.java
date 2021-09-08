import java.util.*;
// 210908

class Solution_LV3_풍선터뜨리기 {
    public int solution(int[] a) {
        int answer = 2; // 제일 왼쪽, 오른쪽은 가능
        if(a.length<=2) return a.length;
        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];
        
        int min = Integer.MAX_VALUE;
        for(int i=0; i<a.length; i++) {
            min = Math.min(min, a[i]);
            leftMin[i] = min;
        }
        min = Integer.MAX_VALUE;
        for(int i=a.length-1; i>=0; i--) {
            min = Math.min(min, a[i]);
            rightMin[i] = min;
        }
        
        for(int i=1; i<a.length-1; i++) {
            if(a[i]>leftMin[i] && a[i]>rightMin[i]) continue;
            ++answer;
        }   
        
        return answer;
    }
}
