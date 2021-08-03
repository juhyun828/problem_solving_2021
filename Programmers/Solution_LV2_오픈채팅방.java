import java.util.*;

class Solution_LV2_오픈채팅방 {
    public String[] solution(String[] record) {
        String[] answer = {};
        int l = 0;
        Map<String, String> map = new HashMap<String, String>(); // 아이디:이름
        
        for(String str: record) {
            String[] arr = str.split(" ");
            if(arr[0].equals("Enter")) {
                map.put(arr[1], arr[2]);
                l++;
                
            } else if(arr[0].equals("Leave")) {
                l++;
                
            } else if(arr[0].equals("Change")) { // 이름 변경
                 map.put(arr[1], arr[2]);
            }
        }
        
        answer = new String[l];
        int idx = 0;
        for(String str: record) {
            String[] arr = str.split(" ");
            if(arr[0].equals("Enter")) {
                String name = map.get(arr[1]);
                answer[idx++] = name + "님이 들어왔습니다.";
                
            } else if(arr[0].equals("Leave")) {
                String name = map.get(arr[1]);
                answer[idx++] = name + "님이 나갔습니다.";  
            } 
        }
        
        return answer;
    }
}