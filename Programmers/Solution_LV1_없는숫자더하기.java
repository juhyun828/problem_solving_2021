// 210916
class Solution_LV1_없는숫자더하기 {
    public int solution(int[] numbers) {
        int answer = 0;
        boolean[] v = new boolean[10];
        for(int num: numbers) 
            v[num] = true;
        
        for(int i=0; i<10; i++) {
            if(!v[i]) answer+=i;
        }
        
        return answer;
    }
}