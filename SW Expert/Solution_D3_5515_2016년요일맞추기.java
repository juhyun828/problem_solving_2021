import java.util.*;
import java.io.*;
// 210504
 
public class Solution_D3_5515_2016년요일맞추기 {          //   1   2   3   4   5   6   7   8   9   10  11  12
    static int[] days = new int[] {0, 30, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = stoi(br.readLine());
         
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            int month = stoi(st.nextToken());
            int day = stoi(st.nextToken());
            int sum = 0;
             
            if(month==1) {
                if(day==1) {
                    sb.append("#" + tc + " " + 4 + "\n");
                    continue;
                }
                sum += day-1;
                int ans = sum%7;
                ans += 4;
                if(ans>=7) ans-=7;
                sb.append("#" + tc + " " + ans + "\n");
                continue;
            }   
             
            for(int i=1; i<month; i++) {
                sum += days[i];
            }
            sum += day;
             
            int ans = sum%7;
            ans += 4;
            if(ans>=7) ans-=7;
            sb.append("#" + tc + " " + ans + "\n");
             
        }
         
        System.out.println(sb.toString());
        br.close();
    }
     
    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}