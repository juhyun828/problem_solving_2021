import java.util.*;

// 210803
class Solution_Lesson6_2_MaxProductOfThree {
    public int solution(int[] A) {
        Arrays.sort(A);
        int len = A.length;
        int ans = A[len-3] * A[len-2] * A[len-1];

        if(A[0]<=0 && A[1]<=0 && A[len-1]>=0) {
            ans = Math.max(ans, A[0]*A[1]*A[len-1]);
        }

        return ans;
    }
}
