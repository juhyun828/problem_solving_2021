import java.io.*;
import java.util.*;
// 210810

public class Main_2012_BJ_등수매기기 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = stoi(br.readLine());
        }

        Arrays.sort(arr);
        long res = 0;

        for(int i=0; i<N; i++) {
            res += Math.abs((i+1) - arr[i]);
        }

        System.out.println(res);

        br.close();
    }
    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}