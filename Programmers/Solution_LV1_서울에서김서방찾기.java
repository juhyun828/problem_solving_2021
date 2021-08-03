import java.util.*;
// 210619

class Solution_LV1_서울에서김서방찾기 {
    public String solution(String[] seoul) {
        String answer = "";
        int idx = Arrays.binarySearch(seoul, "Kim");
		// int idx = Arrays.binarySearch(seoul, "Kim"); 
		// 이진탐색은 이미 정렬된 경우에만 사용 가능
        answer = "김서방은 " + idx+ "에 있다";
        return answer;
    }
}