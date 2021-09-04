import java.io.*;
import java.util.*;
// 210904

public class Main_BJ_20300_서강근육맨 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine().trim());
        long[] arr = new long[N];
        long max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
        for(int i=0; i<N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        if(N%2==1) {
            max = arr[N-1];
            for(int i=0; i<N/2; i++) {
                max = Math.max(max, arr[i] + arr[N-2-i]);
            }

        } else {
            for(int i=0; i<N/2; i++) {
                max = Math.max(max, arr[i] + arr[N-1-i]);
            }
        }

        System.out.println(max);

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}