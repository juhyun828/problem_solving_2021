import java.io.*;
import java.util.*;
// 210920

public class Main_BJ_14675_단절점과단절선 {
    static ArrayList<ArrayList<Integer>> tree;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        tree = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<=N; i++) {
            tree.add(new ArrayList<Integer>());
        }

        StringTokenizer st;
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        int q = stoi(br.readLine());
        for(int i=0; i<q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int t = stoi(st.nextToken());
            int k = stoi(st.nextToken());
            if(t==2) System.out.println("yes");
            else { // 연결된 간선이 2개 이상이면 단절점
                if(tree.get(k).size()>=2) System.out.println("yes");
                else System.out.println("no");
            }
        }

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
