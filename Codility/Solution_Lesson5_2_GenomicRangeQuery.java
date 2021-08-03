// 210802
import java.util.*;

class Solution_Lesson5_2_GenomicRangeQuery {
    static ArrayList<int[]> list;
    public int[] solution(String S, int[] P, int[] Q) {
        int M = P.length;
        int[] ans = new int[M];
        int[] cnt = new int[4];
        list = new ArrayList<int[]>();
        
        // cnt배열로 list를 채운다.
        for(int i=0; i<S.length(); i++) {
            char c = S.charAt(i);
            int num = 0;
            if(c=='A') num = 0;
            else if(c=='C') num = 1;
            else if(c=='G') num = 2;
            else if(c=='T') num = 3;
            cnt[num]++;
            // cnt의 복사본을 list에 넣는다.
            int[] cntCopy = new int[4];
            for(int j=0; j<4; j++) {
                cntCopy[j] = cnt[j];
            }
            list.add(cntCopy);
        }

        for(int i=0; i<M; i++) {
            int start = P[i];
            int end = Q[i];
            int min = calMin(start, end);
            ans[i] = min;
        }

        return ans;
    }

    static int calMin(int start, int end) {
        int[] endArr = new int[4];
        for(int i=0; i<4; i++) {
            endArr[i] = list.get(end)[i];
        }
        if(start!=0) {
            int[] startArr = list.get(start-1);
            for(int i=0; i<4; i++) {
                endArr[i] -= startArr[i];
            }
        }

        // endArr에서 처음으로 배열 값이 0이 되지 않는 idx를 구한다.
        int min = 1;
        for(int i=0; i<4; i++) {
            if(endArr[i]!=0) {
                min = i+1;
                break;
            }
        }

        return min;
    }
}