import java.util.*;
// 210714

class Solution_LV2_뉴스클러스터링_fail {
    public int solution(String str1, String str2) {
        int answer = 0;
        int mul = 65536;
        Set<String> setA = new HashSet<String>();
        Set<String> setB = new HashSet<String>();
        int inter = 0, union = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        // str1의 집합들
        for(int i=0; i<str1.length()-1; i++) {
            if('a'<=str1.charAt(i)&&str1.charAt(i)<='z'
              &&'a'<=str1.charAt(i+1)&&str1.charAt(i+1)<='z') {
                String sub = str1.substring(i, i+2);
                setA.add(sub);
            }
        }
        
        // str2의 집합들
        for(int i=0; i<str2.length()-1; i++) {
            if('a'<=str2.charAt(i)&&str2.charAt(i)<='z'
              &&'a'<=str2.charAt(i+1)&&str2.charAt(i+1)<='z') {
                String sub = str2.substring(i, i+2);
                setB.add(sub);
            }
        }
        
        if(setA.size()==0&&setB.size()==0) {
            return 1*mul;
        }
        
        Iterator<String> itA = setA.iterator();
        while(itA.hasNext()) {
            String subA = itA.next();
            if(setB.contains(subA)) {
                ++inter;
                setB.remove(subA);
            }
        }
        
        union = setA.size()+setB.size();
        
        // 유사도
        double usado = (double)inter / (double)union;
        System.out.println(inter + " " + union);
        answer = (int)(usado*mul);
        
        return answer;
    }
}