import java.io.*;
import java.util.*;
// 210914
// 출처: https://moonsbeen.tistory.com/229

public class Main_BJ_1068_트리_1차원_parents {
    static int N, leaf;
    static int[] parents;
    static boolean[] v;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine());
        parents = new int[N+1]; // i번째 노드의 부모 노드는 parents[i]번 노드다.
        v = new boolean[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int root = -1;
        for(int i=0; i<=N-1; i++) {
            parents[i] = stoi(st.nextToken());
            if (parents[i]==-1) root = i;
        }

        int del = stoi(br.readLine());
        leaf = 0;

        deleteNode(del);
        countLeaf(root);

        System.out.println(leaf);
        br.close();
    }

    static void deleteNode(int del) {
        parents[del] = -2; // 지워진 노드
        for(int i=0; i<=N-1; i++) {
            // del을 부모로 하는 노드도 삭제 처리
            if(parents[i]==del) deleteNode(i);
        }
    }

    static void countLeaf(int node) {
        boolean isLeaf = true;
        v[node] = true;

        // 지워진 노드가 아니라면, 하나라도 자식 노드가 있는지 탐색
        if(parents[node] != -2) {
            for(int i=0; i<=N-1; i++) {
                if(parents[i]==node && !v[i]) {
                    isLeaf = false;
                    countLeaf(i);
                }
            }
            if(isLeaf) ++leaf;
        }

    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}