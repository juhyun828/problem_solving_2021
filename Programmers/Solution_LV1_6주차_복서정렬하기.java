import java.util.*;
// 211006

class Solution_LV1_6주차_복서정렬하기 {
    static class Player implements Comparable<Player>{
        double winRatio;//승률
        int winToHeavier;
        int weight;
        int idx;
        
        public Player(double winRatio,int winToHeavier,int weight,int idx) {
            this.winRatio=winRatio;
            this.winToHeavier=winToHeavier;
            this.weight=weight;
            this.idx=idx;
        }
        
        @Override
        public int compareTo(Player o) {
            if(this.winRatio==o.winRatio) {
                if(this.winToHeavier==o.winToHeavier) {
                    if(this.weight==o.weight){
                           return Integer.compare(this.idx, o.idx); 
                    }
                    return Integer.compare(this.weight, o.weight)*-1; 
                }
                return Integer.compare(this.winToHeavier, o.winToHeavier)*-1; 
            }
            return Double.compare(this.winRatio, o.winRatio)*-1;
        }
    }
    
    public int[] solution(int[] weights, String[] head2head) {
        int n = weights.length;
        int[] answer = new int[n];
        Player[] players = new Player[n];
        
        for(int i=0; i<n; i++) {
            // 전체 승률 구하기
            int cnt=0, winCnt=0, winToHeavier=0;
            for(int j=0; j<n; j++) {
                if(head2head[i].charAt(j)=='N') continue;
                ++cnt;
                if(head2head[i].charAt(j)=='W') {
                    ++winCnt;
                    if(weights[j]>weights[i]) ++winToHeavier;
                }
            }
            double winRatio = (cnt==0)?0:((double)winCnt/(double)cnt);
            players[i] = new Player(winRatio,winToHeavier,weights[i], i);
        }

        Arrays.sort(players);
        for(int i=0; i<n; i++) {
            answer[i] = players[i].idx+1;
        }
        
        return answer;
    }
}