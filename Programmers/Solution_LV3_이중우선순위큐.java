import java.util.*;
// 210629

class Solution_LV3_이중우선순위큐 {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        for(String op: operations) {
            String[] opArr = op.split(" ");
            int num = Integer.parseInt(opArr[1]);
       
            if(opArr[0].equals("I")) {
                minHeap.offer(num);
                maxHeap.offer(num);
            } else {
                if(minHeap.isEmpty()) continue;
                if(num==-1) {
                    int out = minHeap.poll();
                    maxHeap.remove(out);
                    
                } else {
                   int out = maxHeap.poll();
                    minHeap.remove(out); 
                }
            }
        }
        
        if(minHeap.isEmpty()) return answer;
        else {
            answer[0] = maxHeap.poll(); // 최대값
            answer[1] = minHeap.poll(); // 최소값
        }
        return answer;
    }
}