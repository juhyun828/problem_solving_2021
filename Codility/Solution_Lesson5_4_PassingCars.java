// 210803
class Solution_Lesson5_4_PassingCars {
    public int solution(int[] A) {
        int zero = 0, pair = 0;
        for(int n: A) {
            if(n==0) ++zero;
            else pair += zero;
            if(pair > 1000000000) return -1;
        }
        return pair;
    }
}