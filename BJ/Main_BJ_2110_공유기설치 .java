import java.io.*;
import java.util.*;
// 210729

public class Main_BJ_2110_공유기설치 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = stoi(st.nextToken());
        int C = stoi(st.nextToken());
        int[] arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = stoi(br.readLine());
        }

        Arrays.sort(arr);

        int start = arr[1] - arr[0];
        int end = arr[N-1] - arr[0];
        int ans = 0;

        while(start <= end) {
            int mid = (start + end) / 2;
            int val = arr[0];
            int cnt = 1;

            for(int i=1; i<N; i++) {
                if(arr[i] >= val + mid) {
                    val = arr[i];
                    ++cnt;
                }
            }

            if(cnt >= C) {
                start = mid + 1;
                ans = mid;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(ans);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
