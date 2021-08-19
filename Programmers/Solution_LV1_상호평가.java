import java.util.*;
// 210819

class Solution_LV1_상호평가 {
    static int N;
    public String solution(int[][] scores) {
        String answer = "";
        N = scores.length;
        
        for(int j=0; j<N; j++) {
            float avg = 0;
            if(checkOnlyMin(j, scores) || checkOnlyMax(j, scores)) {
                for(int i=0; i<N; i++) { // 자기 자신 제외
                    if(i==j) continue;
                    avg += (float)scores[i][j];
                }    
                avg = avg /(float)(N-1);
            } else {
                for(int i=0; i<N; i++) { // 자기 자신 포함
                    avg += (float)scores[i][j];
                }    
                avg = avg / (float)N;
            }
            
            answer += grade(avg);
        }
        
        return answer;
    }
    
    static String grade(float avg){
        if(avg >= 90) return "A";
        if(avg >= 80) return "B";
        if(avg >= 70) return "C";
        if(avg >= 50) return "D";
        return "F";
    }
    
    static boolean checkOnlyMin(int i, int[][] scores) {
        int mine = scores[i][i];
        int min = 101;
        
        for(int j=0; j<N; j++) {
            if(j==i) continue;
            min = Math.min(min, scores[j][i]);
        }
        
        // 유일한 최소점이면 제거
        if(mine < min) return true;
        else return false;
    }
    
    static boolean checkOnlyMax(int i, int[][] scores) {
        int mine = scores[i][i];
        int max = -1;
        
        for(int j=0; j<N; j++) {
            if(j==i) continue;
            max = Math.max(max, scores[j][i]);
        }
        
        // 유일한 최대점이면 제거
        if(mine > max) return true; 
        else return false;
    }
    
}