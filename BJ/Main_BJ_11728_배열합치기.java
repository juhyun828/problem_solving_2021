import java.io.*;
import java.util.*;
// 210827

public class Main_BJ_11728_배열합치기 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        int[] arrA = new int[N];
        int[] arrB = new int[M];
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
            arrA[i] = stoi(st.nextToken());
        }
        Arrays.sort(arrA);

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++) {
            arrB[i] = stoi(st.nextToken());
        }
        Arrays.sort(arrB);

        int i=0, j=0;

        while (i<N && j<M) {
            if(arrA[i] < arrB[j]) {
                sb.append(arrA[i++] + " ");
            } else {
                sb.append(arrB[j++] + " ");
            }
        }

        while (i<N) {
            sb.append(arrA[i++] + " ");
        }

        while (j<M) {
            sb.append(arrB[j++] + " ");
        }

        System.out.println(sb.toString());

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}