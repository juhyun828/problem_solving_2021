import java.util.*;
// 210722

class Solution_LV2_방금그곡 {
	
    static class Music implements Comparable<Music> {
        int idx; // 순번
        int minute; // 분
        String name;

        public Music(int idx, int minute, String name) {
            this.idx = idx;
            this.minute = minute;
            this.name = name;
        }

        @Override
        public int compareTo(Music o) {
            if(this.minute == o.minute) {
                return Integer.compare(this.idx, o.idx); // 순번 빠른 순
            }
            return Integer.compare(o.minute, this.minute); // 재생시간 긴 순
        }

        @Override
        public String toString() {
            return "Music{" +
                    "idx=" + idx +
                    ", minute=" + minute +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


    public String solution(String m, String[] musicinfos) {
        StringBuilder sb = new StringBuilder();
        int j=0;
        while (j<m.length()) {
            if(j+1<m.length() && m.charAt(j+1)=='#') {
                sb.append(m.substring(j, j+2));
                j+=2;
            } else {
                sb.append(m.substring(j, j+1) + "_");
                j+=1;
            }
        }
        m = sb.toString();

        String answer = "";
        List<Music> res = new ArrayList<Music>();

        for(int i=0; i<musicinfos.length; i++) {
            String[] info = musicinfos[i].split(",");

            int minute = calMinutes(info[0], info[1]);
            String name = info[2];
            String notes = makeNotes(info[3], minute); // 악보
            if(notes.contains(m)) {
                res.add(new Music(i, minute, name));
            }
        }

        if(res.size()==0) {
            answer = "(None)";
        } else {
            Collections.sort(res);
            answer = res.get(0).name;
        }
        
        return answer;
    }

    static String makeNotes(String notes, int minute) {
        ArrayList<String> list = new ArrayList<String>(); // 1분에 1음

        int j=0;
        while (j<notes.length()) {
            if(list.size() >= minute) break; // 분을 넘지 않도록 한다.
            if(j+1<notes.length() && notes.charAt(j+1)=='#') {
                list.add(notes.substring(j, j+2));
                j+=2;
            } else {
                list.add(notes.substring(j, j+1) + "_");
                j+=1;
            }
        }

        // 모자르면 분 시간만큼 더 넣는다.
        int idx = 0, len = list.size();
        while(list.size() < minute) {
            list.add(list.get(idx++%len));
        }

        StringBuilder sb = new StringBuilder();
        for(String s: list) {
            sb.append(s);
        }

        return sb.toString();
    }

    static int calMinutes(String start, String end) {
        String[] startArr = start.split(":");
        String[] endArr = end.split(":");
        int h1 = stoi(startArr[0]);
        int h2 = stoi(endArr[0]);
        int m1 = stoi(startArr[1]);
        int m2 = stoi(endArr[1]);
        return h2*60+m2 - (h1*60+m1);
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}