import java.io.*;
import java.util.*;
// 210827

public class Main_BJ_11659_구간합구하기4 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        int[] prefixSum = new int[N+1];
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++) {
            int num = stoi(st.nextToken());
            prefixSum[i] = prefixSum[i-1] + num;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int total = prefixSum[to] - prefixSum[from-1];
            sb.append(total);
            if(i<M-1) sb.append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}