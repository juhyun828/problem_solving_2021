import java.util.*;
// 210629

class Solution_LV1_K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int c=0; c<commands.length; c++) {
            int i = commands[c][0];
            int j = commands[c][1];
            int k = commands[c][2];
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int z=i; z<=j; z++) {
                list.add(array[z-1]);
            }
            Collections.sort(list);
            answer[c] = list.get(k-1);
        }
        
        return answer;
    }
}