import java.io.*;
import java.util.*;
// 210701
// 투포인터 알고리즘

public class Main_BJ_1806_부분합 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int S = stoi(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = stoi(st.nextToken());
        }
        int p1 = 0, p2 = 0;
        int sum = 0, min = Integer.MAX_VALUE;

        while(true) {
            if(sum >= S) {
                min = Math.min(min, p2-p1);
                sum -= arr[p1++];
            } else {
                if(p2==N) break;
                else sum += arr[p2++];
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? 0 : min);

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}