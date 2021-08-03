import java.io.*;
import java.util.*;
// 210730

public class Main_BJ_1991_트리순회 {
    static Map<Character, Node> tree;
    static class Node {
        char data, left, right;

        public Node(char data, char left, char right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        tree = new HashMap<>();

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            tree.put(input[0].charAt(0), new Node(input[0].charAt(0), input[1].charAt(0), input[2].charAt(0)));
        }

        preOrder(tree.get('A'));
        System.out.println();
        inOrder(tree.get('A'));
        System.out.println();
        postOrder(tree.get('A'));

        br.close();
    }

    static void preOrder(Node node) {
        System.out.print(node.data);
        if(node.left != '.') {
            preOrder(tree.get(node.left));
        }
        if(node.right != '.') {
            preOrder(tree.get(node.right));
        }
    }

    static void inOrder(Node node) {
        if(node.left != '.') {
            inOrder(tree.get(node.left));
        }
        System.out.print(node.data);
        if(node.right != '.') {
            inOrder(tree.get(node.right));
        }
    }

    static void postOrder(Node node) {
        if (node.left != '.') {
            postOrder(tree.get(node.left));
        }
        if (node.right != '.') {
            postOrder(tree.get(node.right));
        }
        System.out.print(node.data);
    }
}
