import java.util.*;
// 210723

class Solution_LV2_파일명정렬 {

    static class File implements Comparable<File> {
        int idx;
        String origin, head, number;

        public File(int idx, String origin, String head, String number) {
            this.idx = idx;
            this.origin = origin;
            this.head = head;
            this.number = number;
        }

        @Override
        public int compareTo(File o) {
            if(this.head.toLowerCase().equals(o.head.toLowerCase())) {
                if(stoi(this.number) == stoi(o.number)) {
                    return Integer.compare(this.idx, o.idx);
                }
                return Integer.compare(stoi(this.number), stoi(o.number));
            }
            return this.head.toLowerCase().compareTo(o.head.toLowerCase());
        }

    }

    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        ArrayList<File> list = new ArrayList<File>();

        for(int i=0; i<files.length; i++) {
            String file = files[i];
            int n=0, t=0;
            // number 시작 위치 찾기
            for(int j=0; j<file.length(); j++) {
                if(file.charAt(j)>='0' && file.charAt(j)<='9') {
                    n = j; t = n+1;
                    break;
                }
            }
            // tail 시작 위치 찾기
            while (t<file.length()) {
                if(file.charAt(t)>='0' && file.charAt(t)<='9') {
                    t++;
                } else {
                    break;
                }
            }
            String head = file.substring(0, n);
            String number = file.substring(n, t);
            list.add(new File(i, file, head, number));
        }
        
        Collections.sort(list);

        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i).origin;
        }
        return answer;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}