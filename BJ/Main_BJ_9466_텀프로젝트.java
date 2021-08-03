import java.io.*;
import java.util.*;
// 210701

public class Main_BJ_9466_텀프로젝트 {
    static int F; // 친구 관계 수 -> 친구 수 <= F*2
    static int[] parents, level;
    static Map<String, Integer> map;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = stoi(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            int num = 0; // 친구 번호
            F = stoi(br.readLine());
            parents = new int[F*2];
            level = new int[F*2];
            for(int i=0; i<F*2; i++) {
                parents[i] = i;
                level[i] = 1;
            }
            map = new HashMap<String, Integer>();
            for(int i=0; i<F; i++) {
                String[] friends = br.readLine().split(" ");
                int from = 0;
                int to = 0;

                if(map.containsKey(friends[0])) {  // 시작 친구
                    from = map.get(friends[0]);
                } else {
                    map.put(friends[0], num++);
                    from = map.get(friends[0]);
                }

                if(map.containsKey(friends[1])) {  // 도착 친구
                    to = map.get(friends[1]);
                } else {
                    map.put(friends[1], num++);
                    to = map.get(friends[1]);
                }

                int cnt = union(from, to);
                System.out.println(cnt);
            }
        }

        br.close();
    }

    static int find(int x){
        if(parents[x]==x) return x;
        return parents[x] = find(parents[x]);
    }

    static int union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) {
            if(x < y) {
                parents[y] = x;
                level[x] += level[y];
            } else {
                parents[x] = y;
                level[y] += level[x];
            }
        }

        if(x < y) return level[x];
        else return level[y];
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}