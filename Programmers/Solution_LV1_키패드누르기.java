import java.util.*;
// 210502

class Solution_LV1_키패드누르기 {
	static int[][] phone = { {3, 1}, //0 
			{0, 0}, {0, 1}, {0, 2}, // 0, 1, 2
			{1, 0}, {1, 1}, {1, 2}, // 4, 5, 6
			{2, 0}, {2, 1}, {2, 2}, // 7, 8, 9
	};
	
   public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        
        // 왼쪽 엄지 손가락의 위치
        int[] leftThumb = new int[] {3, 0};
        // 오른쪽 엄지 손가락의 위치
        int[] rightThumb = new int[] {3, 2};     
    
        for(int num: numbers) {
           	
           	if(num == 1 || num == 4 || num == 7) {
        		sb.append("L");
        		leftThumb[0] = phone[num][0];
          		leftThumb[1] = phone[num][1];
           		
           	} else if(num == 3 || num == 6 || num == 9) {
        		sb.append("R");
        		rightThumb[0] = phone[num][0];
        		rightThumb[1] = phone[num][1];
        		
           	} else {
            	// 1. left 거리
            	int leftDist = calDist(phone[num][0], phone[num][1], leftThumb[0], leftThumb[1]);
            	
            	// 2. right 거리
               	int rightDist = calDist(phone[num][0], phone[num][1], rightThumb[0], rightThumb[1]);
               	// 3. 더 가까운 거리 택 & 손가락 위치 이동
               	if(leftDist < rightDist) { // 왼쪽으로 이동
               		sb.append("L");
               		leftThumb[0] = phone[num][0];
               		leftThumb[1] = phone[num][1];
               		
               	} else if(leftDist > rightDist) { // 오른쪽으로 이동
               		sb.append("R");
               		rightThumb[0] = phone[num][0];
               		rightThumb[1] = phone[num][1];
               		
               	} else if(leftDist == rightDist) { // 왼손잡이, 오른손잡이 인지에 따라 이동
               		if(hand.equals("left")) {
               			sb.append("L");
               			leftThumb[0] = phone[num][0];
               			leftThumb[1] = phone[num][1];
               			
               		} else {
               			sb.append("R");
               			rightThumb[0] = phone[num][0];
               			rightThumb[1] = phone[num][1];
               		}
               	}
           	}

        }
        
        return sb.toString();
    }
   
   static int calDist(int r1, int c1, int r2, int c2) {
	   return Math.abs(r1-r2) + Math.abs(c1-c2);
   }

}
