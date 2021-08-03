// 210713

class Solution_LV1_체육복 {
    static int answer;
    public int solution(int n, int[] lost, int[] reserve) {
        answer = n - lost.length;
        // 여벌 체육복을 도난당한 학생 먼저 처리
        for(int i=0; i<reserve.length; i++) {
            int same = checkSame(lost, reserve[i]);
            if(same>-1) {
                lost[same] = -1;
                reserve[i] = -1;
                ++answer;
            }
        }
        
        for(int i=0; i<reserve.length; i++) {
            lost = checkLost(lost, reserve[i]);
        }
        
        return answer;
    }
    
    static int checkSame(int[] lost, int target) {
        for(int i=0; i<lost.length; i++) {
            if(lost[i]==target) {
                return i;
            }
        }
        return -1;
    }
    
    static int[] checkLost(int[] lost, int target) {
        // target-1 혹은 target+1이 있는지 찾아본다.
        int lostIdx = -1;
        for(int i=0; i<lost.length; i++) {
            if(lost[i]-1==target) {
                lostIdx = i;
                break;
            } else if(lost[i]+1==target) {
                lostIdx = i;
                break;
            }
        }
        if(lostIdx!=-1) {
            ++answer;
            lost[lostIdx] = -1;
        }
        return lost;
    }
}