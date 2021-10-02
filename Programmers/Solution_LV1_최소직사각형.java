import java.util.*;
// 211002

class Solution_LV1_최소직사각형 {
    public int solution(int[][] sizes) {
        int answer = 0;
        int w = 0, h = 0;
        
        for(int[] size: sizes) {
            if (size[0] < size[1]) {
                int tmp = size[1];
                size[1] = size[0];
                size[0] = tmp;
            }
            w = Math.max(w, size[0]);
            h = Math.max(h, size[1]);
        }
        
        answer = w*h;
        return answer;
    }
}