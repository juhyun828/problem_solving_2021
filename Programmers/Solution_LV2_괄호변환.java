import java.util.*;
// 210602

class Solution_LV2_괄호변환 {
    // 균형 잡힌 문자열 인덱스 반환
    static int balancedIndex(String p) {
        int cnt = 0; // 왼쪽 괄호 개수
        for(int i=0; i<p.length(); i++) {
            if(p.charAt(i) == '(') ++cnt;
            else --cnt;
            if (cnt == 0) return i;
        }
        return -1;
    }

    // 올바른 괄호 문자열 판단
    static boolean checkProper(String p) {
        int cnt = 0; // 왼쪽 괄호 개수
        for(int i=0; i<p.length(); i++) {
            if(p.charAt(i) == '(') ++cnt;
            else {
                if(cnt == 0) return false; // 쌍이 맞지 않는 경우
                --cnt;
            }
        }
        return true; // 쌍이 맞는 경우
    }

    public String solution(String p) {
        String answer = "";

        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        if(p.equals("")) return answer;
        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
        // 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
        int idx = balancedIndex(p);
        String u = p.substring(0, idx+1);
        String v = p.substring(idx+1);

        // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
        // 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
        if(checkProper(u)) answer = u + solution(v);

        // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
        else {
            // 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
            answer = "(";
            // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
            answer += solution(v);
            // 4-3. ')'를 다시 붙입니다.
            answer += ")";
            // 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
            u = u.substring(1, u.length()-1);
            String tmp = "";
            for(int i=0; i<u.length(); i++) {
                if(u.charAt(i) == '(') tmp += ")";
                else tmp += "(";
            }
            answer += tmp;
        }
        // 4-5. 생성된 문자열을 반환합니다.
        return answer;
    }
}