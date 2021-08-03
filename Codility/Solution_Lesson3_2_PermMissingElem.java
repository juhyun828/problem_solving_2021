import java.util.*;

class Solution_Lesson3_2_PermMissingElem {
    public int solution(int[] A) {
        boolean[] flag = new boolean[A.length+2];

        for(int num: A) {
            flag[num] = true;
        }

        int ans = 0;
        for(int i=1; i<flag.length; i++) {
            if(!flag[i]) {
                ans = i;
                break;
            }
        }

        return ans;
    }
}