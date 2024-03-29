import java.util.*;

class Solution_Lesson4_1_FrogRiverOne {
    public int solution(int X, int[] A) {
        Set<Integer> set = new HashSet<>();

        for(int i=0; i<A.length; i++) {
            if(A[i]<=X) set.add(A[i]);
            if(set.size()==X) return i;
        }

        return -1;
    }
}