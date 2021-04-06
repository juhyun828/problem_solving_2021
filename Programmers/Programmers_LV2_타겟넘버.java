class Solution {
    static int answer;
    
    static void dfs(int L, int total, int[] numbers, int target) {
        if(L==numbers.length) {
            if(total == target) {
                ++answer;
            }     
            return;
        }
        dfs(L+1, total+numbers[L], numbers, target);
        dfs(L+1, total-numbers[L], numbers, target);
    }
    
    public int solution(int[] numbers, int target) {
        answer = 0;

        dfs(0, 0, numbers, target);
        
        return answer;

    }
}