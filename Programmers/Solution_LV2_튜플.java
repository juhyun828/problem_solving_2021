import java.util.*;
// 210712

class Solution_LV2_튜플 {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(2, s.length()-2);
        
        /*
            1) replace: 바뀌고 싶은 문자열 치환
            2) replaceAll: 정규식 가능-> 특수문자 치환 어려움
            3) replaceFirst: 치환할 문자열에 처음 해당하는 부분만 바꿔줌
        
        */
        s = s.replace("},{", "/");
        String[] arr = s.split("/");
        
        // 1. 문자열 길이 순으로 정렬
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        
        /*
            1) Set: 중복x, 순서x, 
            2) TreeSet: 종류 따라 순서를 만들어 준다
            3) LinkedHashSet: 입력 순서
        */
        Set<Integer> set = new LinkedHashSet<Integer>();
        for(String str: arr) {
            String[] elements = str.split(",");
            for(String e: elements) {
                set.add(Integer.parseInt(e));
            }
        }
        
        answer = new int[set.size()];
        Iterator<Integer> it = set.iterator();
        int idx = 0;
        while(it.hasNext()) {
            answer[idx++] = it.next();
        }
        
        return answer;
    }
}