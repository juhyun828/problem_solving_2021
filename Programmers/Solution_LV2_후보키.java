import java.util.*;
// 210901

class Solution_LV2_후보키 {
    static ArrayList<String > combList, candidates;
    static int N;
    static boolean[] v;

    public int solution(String[][] relation) {
        int answer = 0;
        N = relation[0].length; //4 0~3 

        combList = new ArrayList<String>();
        candidates = new ArrayList<String>();
        v = new boolean[N];

        combination(0,  ""); // 0, 1, 2, 01, 012, ,,,
        
        // Collections.sort(combList, new Comparator<String>(){
        //     @Override
        //     public int compare(String o1, String o2) {
        //         return Integer.compare(o1.length(), o2.length());
        //     }
        // });
        
        for(String str: combList) {
            if(checkUnig(str, relation) && checkMin(str)) {
                candidates.add(str);
            }
        }

        return candidates.size();
    }

    static void combination(int L, String str) {
        if(L==N) {
            if(str.length()>0)
                combList.add(str);
            return;
        }

        combination(L+1, str);
        combination(L+1, str + L);
    }

    // 최소성 검사
    static boolean checkMin(String str) {
        for(String key: candidates) { //0 01
            if(key.length() < str.length()) {
                // 한 글자씩 따져야 한다. key의 모든 요소가 str에 포함되어 있을 때
                boolean flag = false;
                for(String s: key.split("")){
                    if(!str.contains(s)) {
                        flag = true;
                        break;
                    } 
                }
                if(!flag) return false;
            }
        }
        return true;
    }

    // 유일성 검사
    static boolean checkUnig(String str, String[][] relation) { //  013
        String[] arr =  str.split(""); // 013
        Set<String> set = new HashSet<String>();
        for(String[] r: relation) {
            String val = "";
            for(int i=0; i< arr.length; i++ ) {
                val += r[stoi(arr[i])];
            }

            if(val.length()>0 && set.contains(val)) return false;
            else if(val.length()>0 && !set.contains(val)) set.add(val);
        }
        return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}