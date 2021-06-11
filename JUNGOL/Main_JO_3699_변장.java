import java.io.*;
import java.util.*;
// 210609

public class Main_JO_3699_변장 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = stoi(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            int n = stoi(br.readLine());
            Map<String, Integer> map = new HashMap<>();
            int res = 1;

            for(int i=0; i<n; i++) {
                String[] str = br.readLine().split(" ");
                String key = str[1];
                if(map.containsKey(key)) {
                    map.put(key, map.get(key)+1);
                } else {
                    map.put(key, 1);
                }
            }

            for(Integer value: map.values()) {
                res *= (value+1);
            }

            System.out.println(res-1);
        }

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
