import java.util.*;

class Solution_Lesson3_3_TapeEquilibrium {
    public int solution(int[] A) {
        int min = Integer.MAX_VALUE;
        int N = A.length - 1;
        int sum = 0;
        for(int num: A) {
            sum += num;
        }
        int leftSum = 0;

        int p = 1;
        while(true) {
            leftSum += A[p-1];
            int rightSum = sum - leftSum;
            min = Math.min(min, Math.abs(rightSum - leftSum));
            if(p >= N) break;
            else p++;
        }

        return min;
    }
}