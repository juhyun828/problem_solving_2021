import java.io.*;
import java.util.*;
// 210905

public class Main_BJ_14467_소가길을건너간이유1 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        int N = stoi(br.readLine());
        ArrayList[] list = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            list[i] = new ArrayList<Integer>();
        }

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = stoi(st.nextToken());
            int pos = stoi(st.nextToken());
            list[idx].add(pos);
        }

        for(ArrayList<Integer> l: list) {
            if(l.size()<2) continue;

            for(int i=1; i< l.size(); i++) {
                if(l.get(i-1)!=l.get(i)) ++cnt;
            }

        }

        System.out.println(cnt);

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}