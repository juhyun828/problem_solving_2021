import java.util.*;
// 210717 

class Solution_LV2_후보키 {
    static ArrayList<String> candidates; // 순열 결과,후보키리스트
    static String[][] relation;
    public int solution(String[][] inputRelation) {
        int answer = 0;

        relation = inputRelation;
        candidates = new ArrayList<String>();
        
        permutation(0, "", relation[0].length);
        
        Collections.sort(candidates, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2){
                return Integer.compare(o1.length(), o2.length());
            }
        });
        
        List<String> list = new ArrayList<String>();
        for(String c: candidates) {
            list.add(c);
        } 
        
        for(String c: list) {
            if(candidates.contains(c)) {
                candidates = checkMinimality(c);
            }
        }
        
        return candidates.size();
    }
    
    static void permutation(int L, String res, int N) {
        if(L==N) {
            if(res.isEmpty()) return;       
            if(checkUniqueness(res)) {
                // 유일성을 만족
                candidates.add(res);
            }
            return;
        }
        
        permutation(L+1, res+L, N);
        permutation(L+1, res, N);
    }
    
    static boolean checkUniqueness(String res) {
        List<String> list = new ArrayList<String>();
        int[] cols = new int[res.length()];
        int idx=0;
        for(String s: res.split("")) {
            cols[idx++] = Integer.parseInt(s);
        }

        for(String[] r: relation) {
            String str = "";
            for(int c: cols) {
                str += r[c];
            }
            if(list.contains(str)) return false;
            else list.add(str);
        }
        
        return true;
    }
    
    static ArrayList<String> checkMinimality(String key) {
        // candidates복사
        ArrayList<String> list = new ArrayList<String>();
        for(String c: candidates) {
            list.add(c);
        }
        
        // candidates 배열에서 key를 포함하는 문자열을 제거한다.
        for(String c: candidates) {
            if(c.equals(key)) continue;
            if(c.contains(key)) list.remove(c);
        }
        
        return list;
    }
}