import java.io.*;
import java.util.*;
// 210915

public class Main_BJ_1068_트리 {
    static int N, leaf;
    static Node[] tree;
    static class Node {
        int idx, parent;
        ArrayList<Node> children;

        public Node(int idx) {
            this.idx = idx;
            this.children = new ArrayList<Node>();
        }

        @Override
        public String toString() {
            String str = "idx=" + idx +
                    ", parent=" + parent +
                    ", children= ";
            for(Node child : children) {
                str += child.idx + ", ";
            }
            return str;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine());
        int root = -1;
        leaf = 0;
        tree = new Node[N+1];
        for(int i=0; i<N; i++) {
            tree[i] = new Node(i);
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
            int parent = stoi(st.nextToken());
            if(parent==-1) {
                root = i;
                tree[i].parent = parent;
            }
            else {
                tree[i].parent = parent;
                tree[parent].children.add(tree[i]);
            }
        }

        int del = stoi(br.readLine());

        if (del == root) {
            System.out.println(0);
        } else {
            deleteNode(del);
            leaf = countLeft(root);
            System.out.println(leaf);
        }

        br.close();
    }

    static void print() {
        // 출력
        for(int i=0; i<N; i++) {
            System.out.println(tree[i]);
        }
    }

    static void deleteNode(int nodeIdx) {
        int parent = tree[nodeIdx].parent;
        tree[parent].children.remove(tree[nodeIdx]);

        // node번 노드를 부모로 갖는 노드들 삭제
        for(Node child: tree[nodeIdx].children) {
            deleteNode(child.idx);
        }
    }

    static int countLeft(int nodeIdx) {
        if(tree[nodeIdx].children.size()==0) return 1; // node번째 노드가 left노드

        int cnt = 0;
        for(Node child: tree[nodeIdx].children) {
            cnt += countLeft(child.idx);
        }
        return cnt;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}