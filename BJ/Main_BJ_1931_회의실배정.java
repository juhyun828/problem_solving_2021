import java.io.*;
import java.util.*;
// 210921

public class Main_BJ_1931_회의실배정 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());
        int[][] arr = new int[N][2];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = stoi(st.nextToken());
            arr[i][1] = stoi(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]==o2[1]) return Integer.compare(o1[0], o2[0]);
                return Integer.compare(o1[1], o2[1]);
            }
        });

        int cnt = 0;
        int previous_end = -1;
        for(int i=0; i<N; i++) {
            if(arr[i][0]>= previous_end) {
                ++cnt;
                previous_end = arr[i][1];
            }
        }

        System.out.println(cnt);

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}