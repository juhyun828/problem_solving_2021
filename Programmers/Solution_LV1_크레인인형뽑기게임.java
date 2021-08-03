import java.util.*;
// 210711

class Solution_LV1_크레인인형뽑기게임 {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int N = board.length;
        Queue<Integer>[] qArr = new ArrayDeque[N+1];
        for(int i=1; i<=N; i++) {
            qArr[i] = new ArrayDeque<Integer>();
        }

        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(board[r][c]==0) continue;
                qArr[c+1].offer(board[r][c]);
            }
        }

        Stack<Integer> bucket = new Stack<Integer>();
        for(int m: moves) {
            if(qArr[m].isEmpty()) continue;
            
            int cur = qArr[m].poll();
            
            if(bucket.isEmpty()) {
                bucket.push(cur);
                continue;
            }

            if(bucket.peek() == cur) {
                answer += 2;
                bucket.pop();
                
            } else {
                bucket.push(cur);
            }
            
        }
        
        return answer;
    }
}