import java.io.*;
import java.util.*;
// 211018

public class Main_BJ_10775_공항 {
    static int G, P;
    static int[] parents;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = stoi(br.readLine());
        P = stoi(br.readLine());
        parents = new int[G+1];
        for(int i=1; i<=G; i++) {
            parents[i] = i;
        }

        int ans = 0;

        for(int i=0; i<P; i++) {
            int gate = stoi(br.readLine());
            int emptyGate =  find(gate);
            if(emptyGate == 0) break;

            ++ans;
            union(emptyGate, emptyGate-1);
        }

        System.out.println(ans);
        br.close();
    }

    static int find(int x) {
        if(parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x<y) {
            parents[y] = x;
        } else if(x>y) {
            parents[x] = y;
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}