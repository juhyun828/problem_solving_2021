import java.util.*;
// 210714

class Solution_LV1_소수만들기 {
    static int answer;
    static int[] res;
    public int solution(int[] nums) {
        answer = 0;
        res = new int[3];
        combination(0, 0, 0, nums);

        return answer;
    }
    
    static void combination(int L, int cnt, int start, int[] nums) {
        if(cnt==3) {
            int sum=0;
            for(int i: res) {
                sum+=i;
            }
            if(isPrime(sum)) ++answer;
            return;
        }
        if(L>nums.length){
            return;
        }
        
        for(int i=start; i<nums.length; i++){
            res[L] = nums[i];
            combination(L+1, cnt+1, i+1, nums);
        }
    }
    
    static boolean isPrime(int sum) {
        
        for(int i=2; i<sum; i++) {
            if(sum%i==0) return false;
        }
        return true;
    }
}