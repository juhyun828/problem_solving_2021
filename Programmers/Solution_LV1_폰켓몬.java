import java.util.*;
// 210711

class Solution_LV1_폰켓몬 {
    
    public int solution(int[] nums) {
        int max = nums.length/2;
        Set<Integer> set = new HashSet<Integer>();
        for(int n: nums) set.add(n);
        
        if(max > set.size()) max = set.size();
      
        return max;
    }

}