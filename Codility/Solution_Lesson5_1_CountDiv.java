class Solution_Lesson5_1_CountDiv {
    public int solution(int A, int B, int K) {
        int ans = 0;
        if(A==0) {
            ans = B / K + 1; // 0도 포함
        } else {
            ans = B/K - (A-1)/K;
        }
        return ans;
    }
}