// 210712

class Solution_LV2_124나라의숫자 {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        int[] nums = new int[] {4, 1, 2};
        
        while(n>0) {
            int remainder = n%3;
            n/=3;
            
            // answer = nums[remainder] + answer;
            sb.insert(0, nums[remainder]);
            
            if(remainder==0) n-=1;     
        }
        
        return sb.toString();
    }
}