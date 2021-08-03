import java.io.*;
import java.util.*;
// 210617

public class Main_BJ_10809_알파벳찾기 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        int[] check = new int[26];
        Arrays.fill(check, -1);

        for(int i=0; i<arr.length; i++) {
            int idx = arr[i] - 'a';
            if(check[idx]==-1) check[idx] = i;
        }

        for(int i=0; i<26; i++) {
            System.out.print(check[i] + " ");
        }
        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

