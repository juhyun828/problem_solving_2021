import java.io.*;
import java.util.*;
// 210913

public class Main_BJ_5639_이진검색트리 {
    static StringBuilder sb;
    static class Node {
        int num;
        Node left, right;

        public Node(int num) {
            this.num = num;
        }

        public Node(int num, Node left, Node right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }

        void insert(int n) {
            if(n < this.num) {
                if(this.left == null) {
                    this.left = new Node(n);
                } else this.left.insert(n); // 새로운 하위 이진 트리 생성
            } else {
                if(this.right == null) {
                    this.right = new Node(n);
                } else this.right.insert(n); // 새로운 하위 이진 트리 생성
            }
        }
    }

    static void postOrder(Node node) {
        if(node.left != null) postOrder(node.left);
        if(node.right != null) postOrder(node.right);
        sb.append(node.num + "\n");
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        Node root = new Node(stoi(br.readLine()));
        while (true) {
            String input = br.readLine();
            if(input == null || input.length()==0) {
                break;
            }
            root.insert(stoi(input));
        }

        postOrder(root);

        // 출력
        System.out.println(sb.toString());
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}