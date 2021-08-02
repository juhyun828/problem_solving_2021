import java.util.*;
class Solution_Lesson4_3_MissingInteger {
    public int solution(int[] A) {
        int min = 1;
        Arrays.sort(A);
        for(int num: A) {
            if(num==min) ++min;
        }
        return min;
    }
}