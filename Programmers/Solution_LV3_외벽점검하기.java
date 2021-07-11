import java.util.*;
// 210711

class Solution_LV3_외벽점검하기 {
    static boolean[] v;
    static ArrayList<int[]> distList;

    public int solution(int n, int[] weak, int[] dist) {
        int answer = dist.length+1;
        v = new boolean[dist.length];
        distList = new ArrayList<int[]>();
        int weakLen = weak.length;

        // weak 배열을 펼쳐서 원형으로 만든다.
        // 4에서 시작해 반시계방향으로 7칸을 가서 9에 도달하는 것은
        // 9에서 출발해 시계방향으로 7칸을 가서 4(=16)에 도착하는 것과 같다.
        int[] circularWeak = new int[weakLen*2];
        for(int i=0; i<weakLen; i++) {
            circularWeak[i] = weak[i];
        }
        for(int i=weakLen; i<circularWeak.length; i++) {
            circularWeak[i] = weak[i-weakLen] + n;
        }
        
        // dist 배열 순열
        permutation(0, dist, "");

        for(int[] friends: distList) {
            for(int start=0; start<weakLen; start++) {
                int cnt = 1;
                int position = circularWeak[start] + friends[cnt-1];

                for(int idx=start; idx<start+weakLen; idx++) {
                    if(circularWeak[idx] > position) {
                        cnt += 1;
                        if(cnt > dist.length) break;
                        position = circularWeak[idx] + friends[cnt-1];
                    }
                }
                answer = Math.min(answer, cnt);
            }
        }

        if(answer > dist.length) answer = -1;
        return answer;
    }

    static void permutation(int cnt, int[] dist, String str) {
        if(cnt == dist.length) {
            String[] strArr = str.trim().split(" ");
            int[] distRes = new int[dist.length];
            for(int i=0; i<dist.length; i++) {
                distRes[i] = Integer.parseInt(strArr[i]);
            }
            distList.add(distRes);
            return;
        }

        for(int i=0; i<dist.length; i++) {
            if(v[i]) continue;
            v[i] = true;
            permutation(cnt+1, dist, str + dist[i] + " ");
            v[i] = false;
        }
    }
}