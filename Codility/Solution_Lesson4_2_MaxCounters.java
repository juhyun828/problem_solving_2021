class Solution {
    public int[] solution(int N, int[] A) {
        int[] counter = new int[N];
        int max=0, tmpMax=0;

        for(int oper: A) {
            if(oper == N+1) {
                max = tmpMax;
            } else {
                int idx = oper-1;
                counter[idx] = Math.max(counter[idx]+1, max+1);
                tmpMax = Math.max(tmpMax, counter[idx]);
            }
        }

        for(int i=0; i<N; i++) {
            if(counter[i] < max) counter[i] = max; 
        }
        
        return counter;
    }
}