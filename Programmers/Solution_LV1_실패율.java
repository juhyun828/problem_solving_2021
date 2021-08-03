import java.util.*;
// 210715 

class Solution_LV1_실패율 {
    static class Stage implements Comparable<Stage> {
        int idx;
        double ratio; // 실패율
        
        public Stage(int idx, double ratio) {
            this.idx = idx;
            this.ratio = ratio;
        }
        
        @Override
        public int compareTo(Stage o) {
            if(this.ratio == o.ratio) {
                return Integer.compare(this.idx, o.idx);
            }
            return Double.compare(o.ratio, this.ratio);
        }
    }
    
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] fail = new int[N+2];
        int[] total = new int[N+1];
        List<Stage> list = new ArrayList<Stage>();
        
        fail[0] = 0;
        total[0] = stages.length;
        
        for(int n: stages) {
            fail[n]++;
        }
        
        for(int i=1; i<=N; i++) {
            total[i] = total[i-1] - fail[i-1];
            if(total[i]==0) {
                list.add(new Stage(i, 0.0));
            } else {
                double ratio = (double)fail[i] / (double)total[i];
                list.add(new Stage(i, ratio));
            }
        }
        
        Collections.sort(list);
        for(int i=0; i<N; i++) {
            answer[i] = list.get(i).idx;
        }
        
        return answer;
    }
}