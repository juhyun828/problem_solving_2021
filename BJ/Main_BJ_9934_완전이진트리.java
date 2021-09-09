import java.io.*;
import java.util.*;
// 210909

public class Main_BJ_9934_완전이진트리 {
    static int K;
    static int[] arr;
    static ArrayList<Integer>[] list; // list[i]는 i층 번째 노드들

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = stoi(br.readLine());
        int nodeCnt = (int)(Math.pow(2, K)-1); // K층의 완전이진트리 노드 개수는 2^K-1개
        arr = new int[nodeCnt];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<nodeCnt; i++) {
            arr[i] = stoi(st.nextToken());
        }

        list = new ArrayList[K+1];
        for(int i=1; i<=K; i++) {
            list[i] = new ArrayList<Integer>();
        }

        dfs(0, nodeCnt-1, 1);

        // 출력
        for(int i=1; i<=K; i++) {
            for(int node: list[i]) {
                System.out.print(node + " ");
            }
            System.out.println();
        }

        br.close();
    }

    static void dfs(int l, int r, int depth) {
        if(depth == K+1) {
            return;
        }

        int root = (l+r)/2;
        list[depth].add(arr[root]);

        dfs(l, root-1, depth+1); // 왼쪽 자식 노드들 순화
        dfs(root+1, r, depth+1);
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
