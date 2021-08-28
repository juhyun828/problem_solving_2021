import java.io.*;
import java.util.*;
// 210828

public class Main_BJ_22864_피로도 {
    static int addTired, doWork, minusTired, upperTired;
    static int max;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        addTired = stoi(st.nextToken()); // 일 할 때 피로도 10
        doWork = stoi(st.nextToken()); // 처리 일 5
        minusTired = stoi(st.nextToken()); // 쉴 때 줄어드는 피로도 1
        upperTired = stoi(st.nextToken()); // 피로도 상한선 10
        max = 0;
        dfs(0, 0, 0);
        System.out.println(max);

        br.close();
    }

    static void dfs(int work, int tired, int time) {
        if (time>24) return; // 하루에 얼마나 많은 일을 하는지

        max = Math.max(max, work);

        if(tired+addTired<=upperTired) {
            dfs(work+doWork, tired+addTired, time+1);
        }

        if(tired-minusTired>=0) {
            dfs(work, tired-minusTired, time+1);
        } else {
            dfs(work, 0, time+1);
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}