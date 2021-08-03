import java.util.*;

class Solution_Lesson2_1_CyclicRotation {
    public int[] solution(int[] A, int K) {
        int[] res = new int[A.length];

        for(int i=0; i<res.length; i++) {
            res[(i + K) % res.length] = A[i];
        }
        return res;
    }
}
