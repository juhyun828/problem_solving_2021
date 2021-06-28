// 210629

class Solution_LV2_숫자의표현 {
    static int cnt, target;
    public int solution(int n) {
        cnt = 0;
        target = n;

        for(int i=1; i<=target; i++) {
            dfs(i, i);
        }

        return cnt;
    }

    static void dfs(int num, int sum) {
        if(sum==target) {
            ++cnt;
            return;
        }
        if(sum > target) {
            return;
        }
        dfs(num+1, sum+num+1);
    }
}