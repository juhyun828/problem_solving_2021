import java.io.*;
import java.util.*;
// 210701

public class Main_BJ_4195_친구네트워크 {
    static int F, num;
    static int[] parents, level;
    static Map<String, Integer> map;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = stoi(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            num =0;
            map = new HashMap<String, Integer>();
            F = stoi(br.readLine());
            parents = new int[2*F];
            for(int i=0; i<2*F; i++) {
                parents[i] = i;
            }
            level = new int[2*F];
            Arrays.fill(level, 1);

            for(int i=0; i<F; i++) {
                String[] friends = br.readLine().split(" ");
                int f1 = 0, f2 = 0;
                if(map.containsKey(friends[0])) {
                    f1 = map.get(friends[0]);
                } else {
                    f1 = num;
                    map.put(friends[0], num++);
                }
                if(map.containsKey(friends[1])) {
                    f2 = map.get(friends[1]);
                } else {
                    f2 = num;
                    map.put(friends[1], num++);
                }
                union(f1, f2);
                System.out.println(level[find(f1)]);
            }
        }

        br.close();
    }

    static int find(int x) {
        if(parents[x]==x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) {
            parents[y] = x; // x집합에 y를 포함시킨다.
            level[x] += level[y];
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}