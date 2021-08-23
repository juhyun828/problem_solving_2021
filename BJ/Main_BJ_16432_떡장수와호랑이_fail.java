import java.io.*;
import java.util.*;
// 210823

public class Main_BJ_16432_떡장수와호랑이_fail {
    static int N;
    static boolean flag;
    static boolean[] v;
    static ArrayList<ArrayList<Integer>> list;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        flag = false;
        v = new boolean[10];
        N = stoi(br.readLine());
        list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            list.add(new ArrayList<Integer>());
            st = new StringTokenizer(br.readLine(), " ");
            int size = stoi(st.nextToken());
            for(int j=0; j<size; j++) {
                list.get(i).add(stoi(st.nextToken()));
            }
        }

        dfs(0, "");
        if(!flag) System.out.println(-1);

        br.close();
    }

    static void dfs(int L, String res) {
        if(flag) return;
        if(L==N) {
            flag = true;
            res = res.substring(0, res.length()-1);
            System.out.println(res);
            return;
        }

        for(int num: list.get(L)) {
            if(!v[num]) {
                v[num] = true;
                dfs(L+1, res + num + "\n");
                v[num] = false;
            }
        }

    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}