import java.util.*;
// 210629

class Solution_LV2_가장큰수 {
    public String solution(int[] numbers) {
        String answer = "";
        String[] strArr = new String[numbers.length];
        for(int i=0; i<numbers.length; i++) {
            strArr[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(strArr, new Comparator<String> () {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });
        
        if(strArr[0].equals("0")) return "0";
        
        for(String str: strArr) {
            answer += str;
        }
        
        return answer;
    }
}