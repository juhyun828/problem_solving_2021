import java.util.*;
// 210706

class Solution_LV2_기능개발_리스트 {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        ArrayList<Integer> res = new ArrayList<Integer>();
        int len = progresses.length;
        int[] days = new int[len];
        for(int k=0; k<len; k++) {
            int left = (100 - progresses[k]) / speeds[k]; 
            if((100 - progresses[k]) % speeds[k] != 0) ++left;
            days[k] = left;
        }
        
        int i = 0;
        while(true) {
            if(i==len-1) {
                res.add(1);
                break;
            } else if(i >= len) {
                break;
            }
            
            int cnt = 1;
            int before = days[i];
            for(int j=i+1; j<len; j++) {
                if(before >= days[j]) { // 같이 배포
                    cnt++;
                    i = j+1;
                } else {
                    break;
                }
            }
            
            if(cnt==1) {
                res.add(cnt);
                i++;
            } else {
                res.add(cnt);
            }
        }
        
        answer = new int[res.size()];
        for(i=0; i<res.size(); i++) {
            answer[i] = res.get(i);
        }

        return answer;
    }
}