import java.util.*;
// 211002

class Solution_LV2_입실퇴실 {
    public int[] solution(int[] enter, int[] leave) {
        int[] answer = new int[enter.length];
        ArrayList<Integer> list = new ArrayList<Integer>(); // 현재 방 안에 있는 사람들
        int leaveIdx = 0;
        
        for(int i=0; i<enter.length; i++) {
            // 새로 사람이 들어온다.
            int cur = enter[i];
            list.add(cur);
            
            // 아직 방 안에 있는 사람들은 만나는 사람이 한명 늘고,
            // 이번에 들어온 사람들은 원래 방 안에 있던 사람들 만큼 만난다.
            for(int j=0; j<list.size(); j++) {
                if(j==list.size()-1) answer[cur-1] = list.size()-1; // 방금 들어온 자신 빼고
                else answer[list.get(j)-1]++; 
            }
            
            // 나가는 사람들 검사
            while (leaveIdx<answer.length-1) {
                if(list.contains(leave[leaveIdx])) { // 이번에 나갈 사람이 방 안에 있으면 나간다.
                    //list.remove(new Integer(leave[leaveIdx++]));
                    list.remove(Integer.valueOf(leave[leaveIdx++]));
                } else break;
            }
        }
        
        return answer;
    }
}