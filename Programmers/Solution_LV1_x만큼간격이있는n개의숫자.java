// 210716

class Solution_LV1_x만큼간격이있는n개의숫자 {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        long xl = Long.parseLong(Integer.toString(x));
        
        for(int i=1; i<=n; i++) {
            answer[i-1] = xl*i;
        }
        
        return answer;
    }
}