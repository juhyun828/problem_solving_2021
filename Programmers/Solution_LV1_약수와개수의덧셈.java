// 210713
class Solution_LV1_약수와개수의덧셈 {
    public int solution(int left, int right) {
        int answer = 0;
        
        for(int num=left; num<=right; num++) {
            int cnt = count(num);
            if(cnt%2==0) {
                answer += num;
            } else {
                answer -= num;
            }
        }
        
        return answer;
    }
    
    static int count(int num) {
        int cnt = 0;
        
        for(int m=1; m<=num; m++) {
            if(num%m==0) ++cnt;
        }
        
        return cnt;
    }
}