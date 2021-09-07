import java.io.*;
import java.util.*;
// 210907

public class Main_BJ_20115_에너지드링크 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Double> list = new ArrayList<>();
        int N = stoi(br.readLine());

        int[] arr = new int[N];
        int max = 0;
        double total = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = stoi(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        total += (double) max;
        for(int i=0; i<N; i++) {
            if(arr[i]==max) continue;
            //total += arr[i]/2.0;
            total += (double) arr[i] / 2;
        }

        System.out.println(total);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}