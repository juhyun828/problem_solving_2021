import java.io.*;
import java.util.*;
// 210903

public class Main_BJ_11508_21세일 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());
        Integer[] arr = new Integer[N];
        for(int i=0; i<N; i++) {
            arr[i] = stoi(br.readLine());
        }

        int total = 0;
        if(N<3) {
            for(int i=0; i<N; i++) {
                total += arr[i];
            }
        } else {
            Arrays.sort(arr, Collections.reverseOrder());
            int i = 2;
            for(; i<N; i+=3) {
                total += arr[i-2] + arr[i-1];
            }
            i-=2;
            for(; i<N; i++) {
                total += arr[i];
            }
        }

        System.out.println(total);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}