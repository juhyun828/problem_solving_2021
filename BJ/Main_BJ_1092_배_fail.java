import java.io.*;
import java.util.*;
// 210811

public class Main_BJ_1092_배_fail {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        Integer[] crane = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            crane[i] = stoi(st.nextToken());
        }
        int M = stoi(br.readLine());
        ArrayList<Integer> box = new ArrayList<Integer>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            box.add(stoi(st.nextToken()));
        }

        int time = 0;
        Arrays.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());

        if(crane[0] < box.get(0)) {
            time = -1;
        } else {
            loop:
            while (true) {
                if(box.size()==0) break;
                ++time;
                int idx = 0;
                for(int i=0; i<N; i++) {
                    if(box.size()==0) break loop;
                    if(idx==M) break;
                    if(crane[i] >= box.get(idx)) { // 박스 옮기기 가능
                        box.remove(idx);
                    } else {
                        ++idx;
                    }
                }
            }
        }

        System.out.println(time);
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}