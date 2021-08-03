import java.io.*;
import java.util.*;
// 210728

public class Main_BJ_1302_베스트셀러 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<String,Integer> map = new HashMap<>();
        int max = 0;
        ArrayList<String> list = new ArrayList<>();

        for(int i=0; i<N; i++) {
            String key = br.readLine();
            if (map.containsKey(key)) {
                map.put(key, map.get(key)+1);
            } else {
                map.put(key, 1);
            }

            max = Math.max(max, map.get(key));
        }

		// int max = Collections.max(map.values());

        for(String key: map.keySet()) {
            if(max == map.get(key)) {
                list.add(key);
            }
        }

        Collections.sort(list);
        System.out.println(list.get(0));
        br.close();
    }
}
