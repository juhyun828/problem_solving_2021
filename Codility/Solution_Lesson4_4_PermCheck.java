import java.util.*;
class Solution_Lesson4_4_PermCheck {
    public int solution(int[] A) {
        // int N = A.length;
        // int ans = 0;
        // Arrays.sort(A);
        // for(int i=0; i<N-1; i++) {
        //     if(A[i]==A[i+1]) return ans;
        // }
        // if(A[N-1]==N) ans = 1;
        // return ans;
		
	    Arrays.sort(A);
        for(int i=0;i<A.length; i++) {
            if(A[i] != i+1) return 0;
        }

        return 1;
    }
}