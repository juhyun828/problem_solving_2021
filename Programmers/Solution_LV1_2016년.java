import java.util.*;
// 211009

class Solution_LV1_2016년 {
    static int[] days = new int[] {0,
                                  31,29,31,30,31,30, // 1 2 3 4 5 6
                                  31,31,30,31,30,31}; // 7 8 9 10 11 12
    static String[] yoilArr = new String[] {"SUN","MON","TUE","WED","THU","FRI","SAT"};
    static int startYoil = 5; // 금요일
    public String solution(int a, int b) {
        String answer = "";
        int yoil = calYoil(calDiff(a, b));
        answer = yoilArr[yoil];
        
        yoil = calYoil(calDiff(2, 1));
        System.out.print(yoilArr[yoil]);
        
        return answer;
    }
    
    static int calYoil(int diff){
        int yoil = diff%7 + startYoil;
        return yoil%7;
    }
    
    static int calDiff(int m, int d) {
        int diff=0;
        if(m==1) {
            diff = d-1;
        } else {
            diff += days[1]-1;// 1월
            diff += d; // 마지막달
            for(int i=2; i<m; i++) {
                diff += days[i];
            }
        }
        return diff;
    }
}