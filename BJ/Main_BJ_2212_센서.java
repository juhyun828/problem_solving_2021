import java.io.*;
import java.util.*;
// 210811

public class Main_BJ_2212_센서 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine()); // 센서 개수
        int K = stoi(br.readLine()); // 집중국 개수
        int[] sensor = new int[N];
        int total = 0;
        Integer[] diff = new Integer[N-1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            sensor[i] = stoi(st.nextToken());
        }
        // 원점에서 가까운 순으로 센서 정렬
        Arrays.sort(sensor);

        for(int i=1; i<N; i++) {
            diff[i-1] = sensor[i] - sensor[i-1];
            total += diff[i-1];
        }
        Arrays.sort(diff, Collections.reverseOrder());

        // K-1개의 경계를 삭제하면 K개의 그룹으로 분류된다.
        for(int i=0; i<K-1; i++) {
            if(i>=diff.length) break; // 집중국을 겹친다.
            total -= diff[i];
        }

        System.out.println(total);
        
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}