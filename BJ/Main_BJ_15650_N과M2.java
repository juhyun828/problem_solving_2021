import java.io.*;
import java.util.*;
// 210621

public class Main_BJ_15650_N과M2 {
    static int N, M;
    static int[] res;
    static boolean[] isSelected;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        res = new int[M];
        isSelected = new boolean[N + 1];

        combination(0, 0, 1);
        br.close();
    }

    static void combination(int depth, int cnt, int start) {
        if (cnt == M) {
            for(int i=0; i<M; i++){
                System.out.print(res[i] + " ");
            }
            System.out.println();
            return;
        }
        if (depth >= N) {
            return;
        }

        for (int i = start; i <= N; i++) {
            if (isSelected[i])
                continue;
            isSelected[i] = true;
            res[depth] = i;
            combination(depth + 1, cnt + 1, i + 1);
            isSelected[i] = false;
        }
    }
}
