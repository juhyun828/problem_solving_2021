import java.io.*;
import java.util.*;
// 210609

public class Main_JO_1516_단어세기 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String sentence = br.readLine();
            if(sentence.equals("END")) break;
            String[] words = sentence.split(" ");

            // 1. TreeMap -> put하며 key로 자동 정렬
            Map<String, Integer> map = new TreeMap<String, Integer>();
            for (String word : words) {
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            }

            for(Map.Entry<String,Integer> entry: map.entrySet()) {
                sb.append(entry.getKey() + " : " + entry.getValue() + "\n");
            }

             // 2. HashMap - key 정렬
//            HashMap<String, Integer> map = new HashMap<>();
//            Object[] keyArr = map.keySet().toArray();
//            Arrays.sort(keyArr);
//            for(Object key : keyArr) {
//                sb.append(key + " : " + map.get(key)+"\n");
//            }

            // 3. HashMap - value로 정렬
//            ArrayList<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
//            Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
//                @Override
//                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                    if(o1.getValue() == o2.getValue()) {
//                        return o1.getKey().compareTo(o2.getKey());
//                    } else {
//                        return Integer.compare(o1.getValue(), o2.getValue());
//                    }
//                }
//            });

        }

        System.out.println(sb.toString());
        br.close();
    }
}
