import java.io.*;
import java.util.*;
// 210830

public class Main_BJ_1758_알바생강호 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());
        Integer[] arr = new Integer[N];
        for(int i=0; i<N; i++) {
            arr[i] = stoi(br.readLine());
        }
        Arrays.sort(arr, Collections.reverseOrder());

        long max = 0;
        for(int i=0; i<N; i++) {
            int tip = arr[i] - (i+1-1);
            if(tip>=0) max += tip;
        }
        System.out.println(max);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}