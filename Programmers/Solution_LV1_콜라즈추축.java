// 210717
class Solution_LV1_콜라즈추축 {
    public int solution(int num) {
        int answer = 0;
        long numl = num;
        
        while(true) {
            if(answer>=500) {
                answer = -1;
                break;
            }
            if(numl==1) break;
            
            if(numl%2==0) {
                numl/=2;
            } else {
                numl = numl*3 + 1;
            }
            ++answer;
        }
        
        return answer;
    }
}