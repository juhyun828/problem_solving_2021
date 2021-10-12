import java.io.*;
// 211012

public class Main_BJ_2847_게임을만든동준이 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] arr = new int[N];
        int ans = 0;

        for(int i=0; i<N; i++) {
            arr[N-1-i] = stoi(br.readLine());
        }

       for(int i=1; i<N; i++) {
           if(arr[i-1] > arr[i]) continue;
           int target = arr[i-1]-1;
           ans += arr[i] - target;
           arr[i] =target;
       }

        System.out.println(ans);

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
