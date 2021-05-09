// 210508

class Solution_LV1_로또의최고순위와최저순위 {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zero = 0, same = 0;
        boolean[] v = new boolean[46];
        int[] grade = new int[] {6, 6, 5, 4, 3, 2, 1};
        
        for(int num : win_nums) {
            v[num] = true;
        }
        
        for(int num: lottos) {
            if(num==0) {
                ++zero;
            } else {
                if(v[num]) ++same;
            }
        }
        
        // 최저 등수
        int min = 0, max = 0;
        if(same>=2) min = grade[same];
        else min = 6;
        // 최고 등수
        if(same+zero>=2) max = grade[same+zero];
        else max = 6;
        
        return new int[] {max, min};
    }
}