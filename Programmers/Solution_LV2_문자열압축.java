import java.util.*;
// 210607

class Solution_LV2_문자열압축 { 
    // 입력 문자열 길이가 최대 1000이므로 완전탐색 가능

    public int solution(String s) {
        int answer = s.length();
        // 1개 단위(step)부터 압축 단위를 늘려가며 확인
        for(int step=1; step<s.length()/2; step++) {
            String compressed = "";
            String prev = s.substring(0, step); // 앞에서부터 step개 만큼 문자열 추출
            int cnt = 1;
            // 단위(step) 크기만큼 증가시키며 이전 문자열과 비교
            for(int j=step; j<s.length(); j+=step) {
                // 이전 상태와 동일하다면 압축 횟수 증가
                String sub = "";
                for(int k=j; k<j + step; k++) {
                    if(k < s.length()) sub += s.charAt(k);
                }
                if(prev.equals(sub)) cnt+=1;
                // 다른 문자열이 나오면 (더 이상 압축하지 못하는 경우)
                else {
                    if(cnt>=2) compressed += cnt + prev;
                    else compressed += prev;
                    for(int k=j; k<j+step; j++) {
                        if(k<s.length()) sub += s.charAt(k);
                    }
                    prev = sub; // 다시 상태 초기화
                    cnt = 1;
                }
            }
            // 남아있는 문자열 처리
            if(cnt>=2) compressed += cnt + prev;
            else compressed += prev;
            // 만들어지는 압축 문자열이 가장 짧은 것이 정답
            answer = Math.min(answer, compressed.length());
        }
        return answer;
    }
}