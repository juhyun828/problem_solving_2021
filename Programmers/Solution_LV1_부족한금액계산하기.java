// 210806
class Solution_LV1_부족한금액계산하기 {
    public long solution(int price, int money, int count) {
        long answer = 0;
        long total = count * (count+1) / 2 * price;
        if(money < total) {
            answer = total - money;
        }
        return answer;
    }
}