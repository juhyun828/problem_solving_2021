import java.util.*;
// 210619

class Solution_LV1_모의고사 {
    public int[] solution(int[] answers) {
        int[] pA = new int[] {1, 2, 3, 4, 5};
        int[] pB = new int[] {2, 1, 2, 3, 2, 4, 2, 5};
        int[] pC = new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] cnt = new int[3];
        
        for(int i=0; i<answers.length; i++) {
            int cur = answers[i];
            
            if(cur==pA[i%pA.length]) ++cnt[0];
            if(cur==pB[i%pB.length]) ++cnt[1];
            if(cur==pC[i%pC.length]) ++cnt[2];
        }
        
        int max = Math.max(cnt[0], Math.max(cnt[1], cnt[2]));
        ArrayList<Integer> resList = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++) {
            if (max == cnt[i])
                resList.add(i + 1);
        }
        
        int[] answer = new int[resList.size()];
        for(int i=0; i<resList.size(); i++) {
            answer[i] = resList.get(i);
        }
    
        return answer;
    }
}