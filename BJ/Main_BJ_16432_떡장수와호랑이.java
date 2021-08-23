import java.io.*;
import java.util.*;
// 210823

public class Main_BJ_16432_떡장수와호랑이 {
    static int N;
    static boolean flag;
    static boolean[][] v;
    static ArrayList<ArrayList<Integer>> list;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        flag = false;
        N = stoi(br.readLine());
        v = new boolean[N+1][10];
        list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            list.add(new ArrayList<Integer>());
            st = new StringTokenizer(br.readLine(), " ");
            int size = stoi(st.nextToken());
            for(int j=0; j<size; j++) {
                list.get(i).add(stoi(st.nextToken()));
            }
        }

        dfs(0, 0, "");
        if(!flag) System.out.println(-1);

        br.close();
    }

    static void dfs(int L, int prev, String res) {
        if(L==N) {
            flag = true;
            res = res.substring(0, res.length()-1);
            System.out.println(res);
            System.exit(0);
        }

        for(int num: list.get(L)) {
            if(num != prev && !v[L][num]) {
                v[L][num] = true;
                dfs(L+1, num, res + num + "\n");
                // L번째에서 방문해봤는데 갈 수 없다면
                // L+1번째에서도 방문하지 못하게 방문 해제 처리를 하면 안된다.
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}