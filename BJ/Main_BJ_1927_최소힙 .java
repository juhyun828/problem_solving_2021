import java.io.*;
import java.util.*;
// 210731

public class Main_BJ_1927_최소힙 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int N = stoi(br.readLine());
        for(int i=0; i<N; i++) {
            int num = stoi(br.readLine());
            if(num==0) {
                if(pq.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(pq.poll());
                }

            } else {
                pq.offer(num);
            }

        }

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
