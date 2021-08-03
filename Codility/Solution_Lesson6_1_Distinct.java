import java.util.*;
// 210803

class Solution_Lesson6_1_Distinct {
    public int solution(int[] A) {
        Set<Integer> set = new HashSet<Integer>();
        for(int num: A) {
            set.add(num);
        }
        return set.size();
    }
}