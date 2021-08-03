import java.util.*;

class Solution_Lesson2_2_OddOccurrencesInArray {
    public int solution(int[] A) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<A.length; i++) {
            int num = A[i];
            if(!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num)+1);
            }
        }

        for(Integer key: map.keySet()) {
            int value = map.get(key);
            if(value%2==1) {
                ans = key;
                break;
            }
        }
        return ans;
    }
}
