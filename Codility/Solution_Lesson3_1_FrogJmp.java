// 210801
class Solution_Lesson3_1_FrogJmp {
    public int solution(int X, int Y, int D) {
        if(X==Y) return 0;

        int ans = (Y-X)/D;
        if((X+D*ans) < Y) ans += 1;

        return ans;
    }
}