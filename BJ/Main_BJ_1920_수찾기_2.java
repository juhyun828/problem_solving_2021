import java.io.*;
import java.util.*;
// 210726

public class Main_BJ_1920_수찾기_2 {
    static int N, M, max;
    static int[] input;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Set<Integer> set = new HashSet<>();
        N = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
            set.add(stoi(st.nextToken()));
        }

        M = stoi(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++) {
            if(set.contains(stoi(st.nextToken()))) System.out.println(1);
            else System.out.println(0);
        }

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}