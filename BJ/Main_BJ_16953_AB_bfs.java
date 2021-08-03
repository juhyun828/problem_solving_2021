import java.io.*;
import java.util.*;
// 210709

public class Main_BJ_16953_AB_bfs {
    static int A, B;
    static long min;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = stoi(st.nextToken());
        B = stoi(st.nextToken());
        min = Long.MAX_VALUE;
        bfs();

        System.out.println(min==Long.MAX_VALUE ? -1 : min);

        br.close();
    }

    static void bfs() {
        Queue<long[]> q = new ArrayDeque<long[]>();
        q.offer(new long[]{1, A}); // cnt와 A

        while(!q.isEmpty()) {
            long[] cur = q.poll();
            if(cur[1] > B) continue;
            if(cur[1]==B) {
                min = Math.min(min, cur[0]);
                continue;
            }

            q.offer(new long[] {cur[0]+1, cur[1]*2});
            q.offer(new long[] {cur[0]+1, Long.parseLong(Long.toString(cur[1])+"1")});
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}