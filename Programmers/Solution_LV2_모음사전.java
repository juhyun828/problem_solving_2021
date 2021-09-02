import java.util.*;

// 210902
class Solution_LV2_모음사전 {
    static ArrayList<String> list;
    static String[] alp = new String[] {"A", "E", "I", "O", "U"};

    public int solution(String word) {
        int answer = 0;
        list = new ArrayList<String>();
        permutation(0, "");
        Collections.sort(list);

        // answer = list.indexOf(word)+1;
        // answer = find(word);
        answer = binarySearch(word);
        return answer;
    }

    static void permutation(int L, String str) {
        if(L==5) {
            if(str.length()>0 && !list.contains(str))  {
                list.add(str);
            }
            return;
        }
        permutation(L+1, str);
        for(int i=0; i<5; i++) {
            permutation(L+1, str+alp[i]);
        }
    }
    
    static int binarySearch(String word) {
        int left = 0, right = list.size()-1;
        int mid = 0;
        while(left <= right) {
            mid = (left + right)/2;
            
            if(list.get(mid).compareTo(word) > 0) { // mid번째가 사전 순 뒤 -> mid 이전만 탐색
                right = mid-1;
            } else if(list.get(mid).compareTo(word) < 0) { // mid번째가 사전 순 앞 -> mid 이후만 탐색
                left = mid+1;
            } else { // mid번째가 찾던 단어
                return mid+1;
            }
        }
        return mid+1;
    }

    static int find(String word) {
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).equals(word)) {
                return i+1;
            }
        }
        return -1;
    }    
    
}