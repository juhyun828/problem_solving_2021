import java.util.*;
// 210719

public class Solution_LV2_점프와순간이동 {
    public int solution(int n) {
        int ans = 0;
        int plus = 0;
        while(n>2) {
            if(n%2==0) {
                n/=2;
            } else {
                n-=1;
                ++plus;
            }
        }
        
        ans = 1 + plus;
        return ans;
    }
}