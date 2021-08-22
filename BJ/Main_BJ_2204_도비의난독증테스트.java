import java.io.*;
import java.util.*;
// 210822

public class Main_BJ_2204_도비의난독증테스트 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            int n = stoi(br.readLine());
            if(n==0) break;

            ArrayList<String[]> list = new ArrayList<String[]>();
            for(int i=0; i<n; i++) {
                String str = br.readLine();
                list.add(new String[] {str.toLowerCase(), str});
            }

            Collections.sort(list, new Comparator<String[]>(){
                @Override
                public int compare(String[] o1, String[] o2) {
                    return o1[0].compareTo(o2[0]);
                }
            });

            System.out.println(list.get(0)[1]);
        }

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}