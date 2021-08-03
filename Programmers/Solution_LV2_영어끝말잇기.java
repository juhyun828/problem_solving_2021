import java.util.*;

// 210718
class Solution_LV2_영어끝말잇기 {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        
        List<String> list = new ArrayList<String>();
        list.add(words[0]);
        for(int i=1; i<words.length; i++) {
            if((words[i-1].charAt(words[i-1].length()-1)==words[i].charAt(0))
              && !list.contains(words[i])) {
                  list.add(words[i]);
              } else {
                  answer[0] = i%n+1;
                  answer[1] = i/n+1;
                  break;
              }
          
        }
        

        return answer;
    }
}