import java.io.*;
import java.util.*;
// 210615

public class Main_BJ_11054_가장긴바이토닉부분수열 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());
        int[] arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++) {
            arr[i] = stoi(st.nextToken());
        }

        int[] dpLR = new int[N+1];
        int[] dpRL = new int[N+1];

        for(int i=1; i<=N; i++) {
            dpLR[i] = 1;
            for(int j=1; j<i; j++) {
                if(arr[j] < arr[i]) {
                    dpLR[i] = Math.max(dpLR[i], dpLR[j]+1);
                }
            }
        }

        for(int i=N; i>=1; i--) {
            dpRL[i] = 1;
            for(int j=N; j>i; j--) {
                if(arr[j] < arr[i] ) {
                    dpRL[i] = Math.max(dpRL[i], dpRL[j]+1);
                }
            }
        }

        int max = 0;
        for(int i=1; i<=N; i++) {
            max = Math.max(max, dpLR[i]+dpRL[i]);
        }

        System.out.println(max-1);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
