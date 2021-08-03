import java.util.*;
// 210721

class Solution_LV2_쿼드압축후개수세기 {
    static int[] answer;
    public int[] solution(int[][] arr) {
        answer = new int[2];
        
        divide(0, 0, arr.length, arr);
        
        return answer;
    }
    
    static void divide(int startR, int startC, int len, int[][] arr) {
        boolean zero = true, one = true;
    
        for(int r=startR; r<startR+len; r++) {
            for(int c=startC; c<startC+len; c++) {
                if(arr[r][c]==1) zero = false;
                if(arr[r][c]==0) one = false;
            }
        }
        
        
        if(zero) {
            answer[0]++;
            return;
        }
        
        if(one) {
            answer[1]++;
            return;
        }
        
         divide(startR, startC, len/2, arr);
         divide(startR, startC+len/2, len/2, arr);
         divide(startR+len/2, startC, len/2, arr);
         divide(startR+len/2, startC+len/2, len/2, arr);
    }
}