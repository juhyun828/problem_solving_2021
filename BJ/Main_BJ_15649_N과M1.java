import java.io.*;
import java.util.*;
// 210621

public class Main_BJ_15649_N과M1 {
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

        permutation(0, 0);

        br.close();
    }

    static void permutation(int depth, int cnt) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(res[i] + " ");
            }
            System.out.println();
            return;
        }
        if (depth >= N) {
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (isSelected[i])
                continue;
            isSelected[i] = true;
            res[depth] = i;
            permutation(depth + 1, cnt + 1);
            isSelected[i] = false;
        }
    }
}
